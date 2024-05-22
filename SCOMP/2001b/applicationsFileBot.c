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
#include <semaphore.h>
#include <sys/mman.h>

#define EVENT_SIZE (sizeof (struct inotify_event))
#define BUF_LEN ( 1 * ( EVENT_SIZE + 255 ))
#define BUFFER_SIZE 255

int flagFather = 0;
int flagChildren = 0;
int numberChildren;
int new_files=0;

/**
* Handlers the signals sent by the father to the children
*/


void handleChildren(int signo) {
  flagChildren = 1;
}

void handleFatherExit(int signo){
  flagFather = 1;
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

typedef struct {

	char file_name [BUFFER_SIZE];
}shared_memory_data;

// Parameters:
// 1 - input directory
// 2 - output directory
// 3 - number of childrens
// 4 - time of check

int main(int argc,char *argv[]) {

  struct sigaction act2,children_act;

  pid_t pid_handler;
  char outputDirectory[BUFFER_SIZE];
  char inputDirectory[BUFFER_SIZE];

  if (argc < 4) {
    fprintf(stderr, "Usage: %s inputDirectory outputDirectory number_of_children\n", argv[0]);
    exit(1);
  }

  sem_t *semReadWrite; // read and write
  sem_t *semChildren; // father gets a worker
  sem_t *semVerify; //


  if((semReadWrite = sem_open("/sem_read_write", O_CREAT, 0644, 0)) == SEM_FAILED){
	perror("sem_open semReadWrite");
	exit(1);
  }

  if((semChildren = sem_open("/sem_children", O_CREAT, 0644, 0)) == SEM_FAILED){
	perror("sem_open semChildren");
	exit(1);
  }

  if((semVerify = sem_open("/sem_verify", O_CREAT, 0644, 1)) == SEM_FAILED){
	perror("sem_open semVerify");
	exit(1);
  }

  strcpy(inputDirectory, argv[1]);    // passing the inputDirectory as the first parameter
  strcpy(outputDirectory, argv[2]);   // passing the outputDirectory as the second parameter
  numberChildren = atoi(argv[3]);     // 3th parameter is the number of children to be used

  pid_t pid[numberChildren];     // Creating an array to store processes that are working

  int fd;

  shared_memory_data * shm;

  size_t shm_size = sizeof(shared_memory_data);

  if((fd = shm_open("/shm_applicationFileBot", O_CREAT|O_RDWR, S_IRUSR|S_IWUSR)) == -1){
	  perror("shm_open");
	  exit(1);
  }

  if(ftruncate(fd, shm_size) == -1){
	  perror("ftruncate");
	  exit(2);
  }

  if((shm = (shared_memory_data *) mmap(0, shm_size, PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0)) == MAP_FAILED){
	  perror("mmap");
	  exit(3);
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
              sem_wait(semVerify);
              strcpy(shm -> file_name, event->name);
              sem_post(semReadWrite);
            }
          }
        }
      }

      // Clean up inotify resources

      inotify_rm_watch(fd_notify,wd);
      close(fd_notify);
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

      char fileName[BUF_LEN];

      // Continuously read from the pipe until signaled to stop
      while(flagChildren == 0) {

          sem_wait(semChildren);

          strcpy(fileName,shm -> file_name);

          sem_post(semVerify);

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
          // Copy the file to the new directory

          char copyFiles[BUF_LEN*2];
          snprintf(copyFiles, sizeof(copyFiles), "./%s/%s", inputDirectory, fileName);

          // Create a child process to execute the cp command

          pid_t copy = fork();
          if (copy == 0) {
            execlp("cp", "cp", copyFiles, createDirectory, NULL);
          }
          // Signal the parent process that a file has been processed
        }

      exit(0);
    }
  }

  memset(&act2, 0, sizeof(struct sigaction));
  act2.sa_handler = handleFatherExit;
  sigaction(SIGINT, &act2, NULL);

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
  
  char file[BUF_LEN];
  while (flagFather == 0) {
      sem_wait(semReadWrite); // father waits until he can work

      // Generate the candidate report

      strcpy(file,shm -> file_name);

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

      sem_post(semChildren);        
  }

  // Close the report file and the reading end of the pipe

  close(report);

  // Send SIGINT to the handler process and wait for its termination

  int status;
  kill(pid_handler,SIGINT);
  waitpid(pid_handler,&status,0);

  // Send SIGINT to all child processes and wait for their termination

  for (int i = 0; i < numberChildren; i++) {
    kill(pid[i], SIGINT);


    waitpid(pid[i], &status, 0);
  }

  close(fd);
  munmap(shm, shm_size);
  shm_unlink("shm_applicationFileBot");

  sem_unlink("sem_read_write");
  sem_unlink("sem_verify");
  sem_unlink("sem_children");

  return 0;
}
