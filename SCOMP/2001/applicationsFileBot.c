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

// Signal handlers

// Handle the signal sent by the father to the children
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

// Handle the signal sent by the father to the children
void handleFatherExit(int signo){
  flagFather = 1;
}

// Method to put the child as occupied
void children_occupied(pid_t pid) {
  for (int i = 0; i < numberChildren; i++) {
    if(*(childrensAvailability+i) == pid){
      *(childrensAvailability+i) = 0;
    }
  }
}

// Method to get the first available child
pid_t available_process() {
  for (int i = 0; i < numberChildren; i++) {
    if (*(childrensAvailability + i) != 0) {
      return *(childrensAvailability + i);
    }
  }
  return 0;
}

// Function to extract the candidate ID from a string containing the candidate ID followed by a dash '-'
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

  if (argc != 4) {
    fprintf(stderr, "Usage: %s inputDirectory outputDirectory number_of_children\n", argv[0]);
    exit(1);
  }

  strcpy(inputDirectory, argv[1]); 
  strcpy(outputDirectory, argv[2]);
  numberChildren = atoi(argv[3]);

  pid_t pid[numberChildren];     // Creating an array to store processes that are working
  int fd[numberChildren][2];     // Creating an array of pipes for the father to communicate with the children
  int pipe_info[2];               // Pipe to send the new files
  childrensAvailability = (pid_t *) calloc(numberChildren,sizeof(pid_t));   // Array to store the availability of the children

  if (childrensAvailability == NULL) {
    perror("Memory allocation failed");
    return 1;
  }

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

  if (pid_handler==0) {
      memset(&children_act, 0, sizeof(struct sigaction)); 
      children_act.sa_handler = handleChildren; 
      sigaction(SIGINT, &children_act, NULL);

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


      while(flagChildren==0){
        while((length = read(fd_notify, buffer, BUF_LEN))>0){

          for (char *ptr = buffer; ptr < buffer + length;
                        ptr += sizeof(struct inotify_event) + event->len) {

            event = (const struct inotify_event *) ptr;

            /* Print the name of the file. */

            if (event->len){
              char file[BUF_LEN];
              strcpy(file, event->name);
              write(pipe_info[1], file, BUF_LEN);
            }
          }
        }
      }

      inotify_rm_watch(fd_notify,wd);
      close(fd_notify);
      close(pipe_info[1]);
      exit(0);
  }

  for (int i = 0; i < numberChildren; i++) {   // Creation of all working processes
    pid[i] = fork();

    if (pid[i] < 0) {
      perror("Fork failed");
      exit(1);
    }

    if (pid[i] == 0) {
      memset(&children_act, 0, sizeof(struct sigaction)); 
      children_act.sa_handler = handleChildren; 
      sigaction(SIGINT, &children_act, NULL);
      
      close(fd[i][1]);  // Close the write end of the pipe
      char fileName[BUF_LEN];

      while(flagChildren == 0) {

        while((n = read(fd[i][0], fileName, BUF_LEN))>0){
          // Extract the candidate ID from the file name

          char folderName[BUF_LEN];
          char *candidate = extractCandidateID(fileName);

          strcpy(folderName, candidate);

          free(candidate);
          
          // Create the directory

          char createDirectory[BUF_LEN*2];
          snprintf(createDirectory, sizeof(createDirectory), "./%s/%s/", outputDirectory, folderName);

          pid_t mkdir = fork();
          if (mkdir == 0) {
            execlp("mkdir", "mkdir", createDirectory, NULL);
          }

          int status;
          waitpid(mkdir, &status, 0);
          
          // Copy the file to the new directory

          char copyFiles[BUF_LEN*2];
          snprintf(copyFiles, sizeof(copyFiles), "./%s/%s", inputDirectory, fileName);
          
          pid_t copy = fork();
          if (copy == 0) {
            execlp("cp", "cp", copyFiles, createDirectory, NULL);
          }

          kill(getppid(), SIGUSR2);
        }

      }
      close(fd[i][0]);
      exit(0);
    }
  }

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

  close(pipe_info[1]);

  
  char reportFileName[BUF_LEN];
  snprintf(reportFileName, sizeof(reportFileName), "./%s/report.txt", outputDirectory);
  int report=open(reportFileName,O_RDWR);
  if(report == -1){
      printf("\nError Opening File!!\n");
      exit(1);
  }

  while (flagFather == 0) {
      char file[BUF_LEN];
      while((n=read(pipe_info[0],file,BUF_LEN))>0){
        pid_t pid_available=available_process();

        
        while(pid_available==0){
          pid_available=available_process(); //while loop to wait for one process to be available
        }

        int process=0;

        for (int i = 0; i < numberChildren; i++) {
          if (pid[i] == pid_available) {              // If child is available, put it occupied using the method and...
            process=i;       // Send the file to the child
          }
        }            // If child is available, put it occupied using the method and...

        children_occupied(pid_available);
        write(fd[process][1],file,BUF_LEN);       // Send the file to the child

        char candidateReport[BUF_LEN*5];
        char candidateFolder[BUF_LEN*2];
        char id[BUF_LEN];
        char *candidate = extractCandidateID(file);
        strcpy(id, candidate);
        free(candidate);

        memset(candidateReport,0,BUF_LEN);
        snprintf(candidateFolder, sizeof(candidateFolder), "%s/%s", outputDirectory, id);
        snprintf(candidateReport, sizeof(candidateReport), "\nCandidate ID: %s\nOutput Directory: %s\nFile: %s\n", id,candidateFolder, file);


        write(report,candidateReport,BUF_LEN);
      }
  }

  close(report);
  close(pipe_info[0]);

  int status;
  kill(pid_handler,SIGINT);
  waitpid(pid_handler,&status,0);

  for (int i = 0; i < numberChildren; i++) {
    kill(pid[i], SIGINT);
    

    waitpid(pid[i], &status, 0);
  }

  free(childrensAvailability);

  return 0;
}