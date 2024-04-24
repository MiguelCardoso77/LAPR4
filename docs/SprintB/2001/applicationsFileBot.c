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

#define EVENT_SIZE 1
#define BUF_LEN (1024 * (EVENT_SIZE + 16))
#define BUFFER_SIZE 100

volatile sig_atomic_t flagFather = 0; 
volatile sig_atomic_t flagChildren = 0;
volatile pid_t *childrensAvailability;
volatile int numberChildren;
volatile int new_files=0;

// Signal handlers

// Handle the signal sent by the father to the children
void handleChildren(int signo) {
  flagChildren = 1;
}

void handleFather_USR2(int signo, siginfo_t *sinfo, void *context) {
  for (int i = 0; i < numberChildren; i++) {
    if (*(childrensAvailability+i) == 0) {
      *(childrensAvailability+i) = sinfo -> si_pid;
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
}

// Function to extract the candidate ID from a string containing the candidate ID followed by a dash '-'
char* extractCandidateID(char* fileName) {
  char extracted[100];
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
  pid_t pid_handler;
  int periodic_check;
  char outputDirectory[BUFFER_SIZE];
  char inputDirectory[BUFFER_SIZE];

  if (argc < 5) {
    fprintf(stderr, "Usage: %s inputDirectory outputDirectory number_of_children periodic_check\n", argv[0]);
    exit(0);
  }

  strcpy(inputDirectory, argv[1]); 
  strcpy(outputDirectory, argv[2]);
  periodic_check = atoi(argv[4]);
  numberChildren = atoi(argv[3]);

  pid_t pid[numberChildren];     // Creating an array to store processes that are working
  int fd[numberChildren][2];     // Creating an array of pipes for the father to communicate with the children
  int pipe_info[2];               // Pipe to send the new files
  childrensAvailability = (pid_t *) calloc(numberChildren,sizeof(pid_t));   // Array to store the availability of the children

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

  if (pid_handler==0) {
      close(pipe_info[0]);
      int fd_notify, wd;
      char buffer[BUF_LEN];

      fd_notify = inotify_init();   // Waiting for new files at directory
      if (fd_notify < 0) {
          perror("inotify_init");
          exit(EXIT_FAILURE);
      }

      wd = inotify_add_watch(fd_notify, inputDirectory, IN_CREATE);    // Configs what directory to watch
      if (wd == -1) {
          perror("inotify_add_watch");
          exit(EXIT_FAILURE);
      }

      while (flagChildren == 0) {
          int length = read(fd_notify, buffer, BUF_LEN);
          if (length < 0) {
              perror("read");
              exit(EXIT_FAILURE);
          }

          char *ptr = buffer;
          while (ptr < buffer + length) {
              struct inotify_event *event = (struct inotify_event *)ptr;

              if (event->mask & IN_CREATE) {
                  write(pipe_info[1], event->name, sizeof(BUFFER_SIZE));
              }

              ptr += sizeof(struct inotify_event) + event->len;
          }
      }
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
      while(flagChildren == 0) {
        close(fd[i][1]);  // Close the write end of the pipe
        char fileName[BUFFER_SIZE];

        while ((n = read(fd[i][0], fileName, sizeof(fileName))) != 0) {

          // Extract the candidate ID from the file name

          char folderName[100];
          strcpy(folderName, extractCandidateID(fileName));
          
          // Create the directory

          char createDirectory[100];
          snprintf(createDirectory, sizeof(createDirectory), "./%s/%s", outputDirectory, folderName);

          pid_t mkdir = fork();
          if (mkdir == 0) {
            execlp("mkdir", "mkdir", createDirectory, NULL);
          }
          
          // Copy the file to the new directory

          char copyFiles[100];
          snprintf(copyFiles, sizeof(copyFiles), "./%s/%s", inputDirectory, fileName);
          
          pid_t copy = fork();
          if (copy == 0) {
            execlp("cp", "cp", copyFiles, createDirectory, NULL);
          }

        }

        kill(getppid(), SIGUSR2);
      }

      close(fd[i][0]);
      exit(0);
    }
  }

  close(pipe_info[1]);

  while (flagFather == 0) {
      char file[BUFFER_SIZE];
      while((n=read(pipe_info[0],file,sizeof(BUFFER_SIZE)))!=0){
      pid_t pid_available = available_process();
      for (int i = 0; i < numberChildren; i++) {
        if (pid[i] == pid_available) {              // If child is available, put it occupied using the method and...
          children_occupied(pid_available);
          write(fd[i][1],file,sizeof(BUFFER_SIZE));       // Send the file to the child 
        }
      }
    }
  }

  close(pipe_info[0]);

  for (int i = 0; i < numberChildren; i++) {
    kill(pid[i], SIGKILL);

    int status;
    waitpid(pid[i], &status, 0);
  }

  free(childrensAvailability);

  return 0;
}