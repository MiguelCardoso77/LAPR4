#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <fcntl.h>
#include <time.h>
#include <sys/inotify.h>

#define BUF_LEN (1024 * (EVENT_SIZE + 16))
#define BUFFER_SIZE 100;
#define BASE_DIRECTORY "applicationsFileBotOutput"

void handle_children(int signo,siginfo_t *sinfo, void *context) {
  flagChildren = 1;
}

void handle_father_usr2(int signo,siginfo_t *sinfo, void *context) {
  for (int i = 0; i < number_children; i++) {
    if (*(childrens_availability+i) == 0) {
      *(childrens_availability+i)=sinfo->si_pid;
    }
  }
  
}

void children_occupied(pid_t pid) {
  for (int i = 0; i < number_children; i++) {
    if(*(childrens_availability+i) == pid){
      *(childrens_availability+i) = 0;
    }
  }
}

volatile sig_atomic_t flagFather = 0; 
volatile sig_atomic_t flagChildren = 0;
volatile pid_t *childrens_availability;
volatile int number_children;
volatile int new_files=0;

void handle_father_exit(int signo,siginfo_t *sinfo, void *context){
  flagFather = 1;     // When the father receives the signal, it will stop the children
}

pid_t available_process() {
  for (int i = 0; i < number_children; i++) {
    if (*(childrens_availability+i) != 0) {
      return *(childrens_availability+i);
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

// Function to check if a directory exists, and if not, create it
int createOutputDirectory(const char *path) {
  struct stat info;
  if (stat(path, &info) != 0) {
      // Directory doesn't exist, try creating it
      if (mkdir(path, 0777) == -1) {
          perror("mkdir");
          return 0;
      } else {
          return 1;
      }
  } else if (info.st_mode & S_IFDIR) {
      return 1;
  } else {
      return 0;
  }
}

// Parameters:
// 1 - input directory
// 2 - output directory
// 3 - number of childrens
// 4 - time of check
int main(int argc,char *argv[]) {
  pid_t pid_handler;
  int periodic_check,number_of_files;
  char output_directory[BUFFER_SIZE];
  char input_directory[BUFFER_SIZE];


  output_directory=argv[2];
  input_directory=argv[1];
  periodic_check=atoi(argv[4]);
  number_children=atoi(argv[3]);
  pid_t pid[number_children];     // Creating an array to store processes that are working
  int fd[number_children][2];     // Creating an array of pipes for the father to communicate with the children
  int pipe_info[2];               // Pipe to send the new files
  childrens_availability=(pid_t *) calloc(number_children,sizeof(pid_t));   // Array to store the availability of the children

  for (int i = 0; i < number_children; i++) {
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

      wd = inotify_add_watch(fd_notify, input_directory, IN_CREATE);    // Configs what directory to watch
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

          while (char *ptr < buffer + length) {
              struct inotify_event *event = (struct inotify_event *)ptr;

              if (event->mask & IN_CREATE) {
                  write(pipe_info[1],event->name,sizeof(BUFFER_SIZE));
              }
              ptr += sizeof(struct inotify_event) + event->len;
          }
      }
      close(pipe_info[1]);
      exit(0);
  }

  for (int i = 0; i < number_children; i++) {   // Creation of all working processes
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
          char folderName[100];
          strcpy(folderName, extractCandidateID(fileName)); // Copy the result of extractCandidateID into folderName
          
          char path[100];
          snprintf(path, sizeof(path), "%s/%s", BASE_DIRECTORY, folderName);
          int directorySuccessful = createOutputDirectory(path);

          if (directorySuccessful == 1) {
              // TO DO: Copy files to the created directory
              // TO DO: Decide which folder to create based on the file names
          } else {
              printf("Failed to create directory: %s\n", path);
              // Handle directory creation failure
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
      pid_t pid_available=available_process();
      for (int i = 0; i < number_children; i++) {
        if (pid[i] == pid_available) {              // If child is available, put it occupied using the method and...
          children_occupied(pid_available);
          write(fd[i][1],file,sizeof(BUFFER_SIZE));       // Send the file to the child 
        }
      }
    }
  }

  close(pipe_info[0]);

  for (int i = 0; i < number_children; i++) {
    kill(pid[i],SIGKILL);
    waitpid(pid[i]);
  }

  return 0;
}