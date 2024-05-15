#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/inotify.h>
#include <signal.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>

#define EVENT_SIZE (sizeof (struct inotify_event))
#define BUF_LEN ( 1 * ( EVENT_SIZE + 255 )) 
#define BUFFER_SIZE 255

int flagFather = 0; 
int flagChildren = 0;
pid_t *childrensAvailability;
int numberChildren;
int new_files=0;

/**
* Handlers the signals sent by the father to the children
*/

void handleChildren(int signo) {
  flagChildren = 1;
}

void handleFather_USR2(int signo, siginfo_t *sinfo, void *context) {
  for (int i = 0; i < numberChildren; i++) {
    if (*(childrensAvailability+i) == 0) {
      *(childrensAvailability+i) = sinfo->si_pid;
    }
  }
}

void handleFatherExit(int signo){
  flagFather = 1;
}

/**
* Function that puts the children whose pid is passed by parameter occupied
*/

void children_occupied(pid_t pid) {
  for (int i = 0; i < numberChildren; i++) {
    if(*(childrensAvailability+i) == pid){
      *(childrensAvailability+i) = 0;
    }
  }
}

/**
* Function to get the first available child
*/

pid_t available_process() {
  for (int i = 0; i < numberChildren; i++) {
    if (*(childrensAvailability + i) != 0) {
      return *(childrensAvailability + i);
    }
  }
  return 0;
}

/**
 * Function to extract the candidate ID from a string containing the candidate's ID followed by '-'
 */

char* extractCandidateID(char* fileName) {
  char* extracted = (char*)malloc(BUF_LEN * sizeof(char));

  if (extracted == NULL) {
    // Handle allocation failure
    fprintf(stderr, "Memory allocation failed\n");
    exit(EXIT_FAILURE);
  }
  int i;

  for (i = 0; fileName[i] != '-' && fileName[i] != '\0'; i++) {
    extracted[i] = fileName[i];
  }

  extracted[i] = '\0';

  return extracted;
}

// Parameters:
// 1 - input directory
// 2 - output directory
// 3 - number of childrens
// 4 - time of check

int main(int argc,char *argv[]) {

  struct sigaction act,act2,children_act; 

  pid_t pid_handler;
  int n;
  char outputDirectory[BUFFER_SIZE];
  char inputDirectory[BUFFER_SIZE];

  if (argc < 4) {
    fprintf(stderr, "Usage: %s inputDirectory outputDirectory number_of_children\n", argv[0]);
    exit(1);
  }

  strcpy(inputDirectory, argv[1]);    // passing the inputDirectory as the first parameter
  strcpy(outputDirectory, argv[2]);   // passing the outputDirectory as the second parameter
  numberChildren = atoi(argv[3]);     // 3th parameter is the number of children to be used

  pid_t pid[numberChildren];     // Creating an array to store processes that are working
  int fd[numberChildren][2];     // Creating an array of pipes for the father to communicate with the children
  int pipe_info[2];              // Pipe to send the new files
  childrensAvailability = (pid_t *) calloc(numberChildren,sizeof(pid_t));   // Array to store the availability of the children

  if (childrensAvailability == NULL) {
    perror("Memory allocation failed");
    exit(1);
  }

  /**
  * Pipes creation
  */

  for (int i = 0; i < numberChildren; i++) {
    if(pipe(fd[i]) == -1){ 
      perror("Pipe failed"); 
      exit(1); 
    }
  }

  if (pipe(pipe_info) == -1) { 
      perror("Pipe failed"); 
      exit(1); 
  }

  pid_handler = fork();      // Creating the process that warns the father of new files 

  if (pid_handler < 0) {
          perror("fork failed");
          exit(1);
      }

  /**
  * Directory monotorization
  */

  if (pid_handler==0) { // child process to monitor new archives
      memset(&children_act, 0, sizeof(struct sigaction)); 
      children_act.sa_handler = handleChildren; 
      sigaction(SIGINT, &children_act, NULL);

      // Close the reading end of the pipe
      close(pipe_info[0]);
      int fd_notify, wd;
      char buffer[BUF_LEN];

      fd_notify = inotify_init();   // Waiting for new files at directory
      if (fd_notify < 0) {
          perror("inotify_init");
          exit(1);
      }

      wd = inotify_add_watch(fd_notify, inputDirectory, IN_CREATE | IN_MOVED_TO);    // Configs what directory to watch
      if (wd == -1) {
          perror("inotify_add_watch");
          exit(1);
      }

      const struct inotify_event *event;
      ssize_t length;

      // Monitor new files until signaled to stop
      while(flagChildren==0){
        while((length = read(fd_notify, buffer, BUF_LEN))>0){

          for (char *ptr = buffer; ptr < buffer + length;
                        ptr += sizeof(struct inotify_event) + event->len) {

            event = (const struct inotify_event *) ptr;

            // Send the name of the new file through the pipe
            if (event->len){
              char file[BUF_LEN];
              strcpy(file, event->name);
              write(pipe_info[1], file, BUF_LEN);
            }
          }
        }
      }

      // Clean up inotify resources

      inotify_rm_watch(fd_notify,wd);
      close(fd_notify);
      close(pipe_info[1]);
      exit(0);
  }

  for (int i = 0; i < numberChildren; i++) {   // Creation of all working processes (childs)
    pid[i] = fork();

    if (pid[i] < 0) {
      perror("Fork failed");
      exit(1);
    }

    /**
    * Copying the files
    */

    if (pid[i] == 0) { // Child process to handle the file processing
      memset(&children_act, 0, sizeof(struct sigaction)); 
      children_act.sa_handler = handleChildren; 
      sigaction(SIGINT, &children_act, NULL);

      // Close the writing end of the pipe
      close(fd[i][1]);
      char fileName[BUF_LEN];

      // Continuously read from the pipe until signaled to stop
      while(flagChildren == 0) {
        // Read the file name from the pipe
        while((n = read(fd[i][0], fileName, BUF_LEN))>0){
          // Extract the candidate ID from the file name

          char folderName[BUF_LEN];
          char *candidate = extractCandidateID(fileName);

          strcpy(folderName, candidate);

          free(candidate);
          
          // Create the directory for the candidate

          char createDirectory[BUF_LEN*2];
          snprintf(createDirectory, sizeof(createDirectory), "./%s/%s/", outputDirectory, folderName);

          // Create a child process to execute the mkdir command

          pid_t mkdir = fork();
          if (mkdir == 0) {
            execlp("mkdir", "mkdir", createDirectory, NULL);
          }

          // Waits for the mkdir command to finish

          int status;
          waitpid(mkdir, &status, 0);
          
          // Copy the file to the new directory

          char copyFiles[BUF_LEN*2];
          snprintf(copyFiles, sizeof(copyFiles), "./%s/%s", inputDirectory, fileName);

          // Create a child process to execute the cp command

          pid_t copy = fork();
          if (copy == 0) {
            execlp("cp", "cp", copyFiles, createDirectory, NULL);
          }
          // Signal the parent process that a file has been processed

          kill(getppid(), SIGUSR2);
        }

      }
      // Close the reading end of the pipe and exit
      close(fd[i][0]);
      exit(0);
    }
  }

  // Assign child process IDs to the children availability array

  for (int i = 0; i < numberChildren; i++)
  {
      *(childrensAvailability+i)=pid[i];
  }
  
  memset(&act, 0, sizeof(struct sigaction)); 
  act.sa_sigaction = handleFather_USR2; 
  act.sa_flags = SA_SIGINFO; 
  sigaction(SIGUSR2, &act, NULL);

  memset(&act2, 0, sizeof(struct sigaction)); 
  act2.sa_handler = handleFatherExit; 
  sigaction(SIGINT, &act2, NULL);

  // Close the writing end of the pipe

  close(pipe_info[1]);

  /**
  * Report generation
  */

  // Open the report file
  char reportFileName[BUF_LEN];
  snprintf(reportFileName, sizeof(reportFileName), "./%s/report.txt", outputDirectory);
  int report=open(reportFileName,O_RDWR);
  if(report == -1){
      printf("\nError Opening File!!\n");
      exit(1);
  }

  // Continuously read from the pipe until signaled to stop
  while (flagFather == 0) {
      char file[BUF_LEN];
      while((n=read(pipe_info[0],file,BUF_LEN))>0){

        // Get an available child process

        pid_t pid_available=available_process();

        // Wait until a child process is available

        while(pid_available==0){
          pid_available=available_process(); //while loop to wait for one process to be available
        }

        int process;

        // Find the index of the available child process

        for (int i = 0; i < numberChildren; i++) {
          if (pid[i] == pid_available) {              // If child is available, put it occupied using the method and...
            process=i;       // Send the file to the child
          }
        }            // If child is available, put it occupied using the method and...

        children_occupied(pid_available);          // Mark the child process as occupied and send the file to it
        write(fd[process][1],file,BUF_LEN);        // Send the file to the child

        // Generate the candidate report

        char candidateReport[BUF_LEN*5];
        char candidateFolder[BUF_LEN*2];
        char id[BUF_LEN];
        char *candidate = extractCandidateID(file);
        strcpy(id, candidate);
        free(candidate);

        memset(candidateReport,0,BUF_LEN);
        snprintf(candidateFolder, sizeof(candidateFolder), "%s/%s", outputDirectory, id);
        snprintf(candidateReport, sizeof(candidateReport), "\nCandidate ID: %s\nOutput Directory: %s\nFile: %s\n", id,candidateFolder, file);

        // Write the candidate report to the report file
        write(report,candidateReport,BUF_LEN);
      }
  }

  // Close the report file and the reading end of the pipe

  close(report);
  close(pipe_info[0]);

  // Send SIGINT to the handler process and wait for its termination

  int status;
  kill(pid_handler,SIGINT);
  waitpid(pid_handler,&status,0);

  // Send SIGINT to all child processes and wait for their termination

  for (int i = 0; i < numberChildren; i++) {
    kill(pid[i], SIGINT);
    

    waitpid(pid[i], &status, 0);
  }

  // Free dynamically allocated memory

  free(childrensAvailability);

  return 0;
}