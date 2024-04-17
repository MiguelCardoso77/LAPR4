#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <fcntl.h>
#include <time.h>
#define BUFFER_SIZE 100;

void handle_children(int signo){
  flagChildren=1;
}

void handle_father_usr2(int signo){
  //criar array para verificar disponibilidade dos filhos
}

volatile sig_atomic_t flagFather = 0; 
volatile sig_atomic_t flagChildren = 0;
volatile pid_t *childrens_availability;


void handle_father_exit(int signo){
  flagFather=1;
}

int get_number_of_files(char *directory){
  int count = 0;
  struct dirent *entry;
  DIR *dir;

  dir = opendir(directory);
  if (dir == NULL) {
    fprintf(stderr, "Could not open directory %s\n", directory);
    continue;
  }

  while ((entry = readdir(dir)) != NULL) {
    if(entry->d_type == DT_REG){ 
      count++;
    }
  }
  closedir(dir);

  return count;
}

int available_process(int *available,int size){
  for (int i = 0; i < size; i++)
  {
    if(*available == 0){
      return i;
    }
  }
}

// Parameters:
// 1 - input directory
// 2 - output directory
// 3 - number of childrens
// 4 - time of check
int main(int argc,char *argv[]){
DIR *dir;
pid_t pid_handler;
int periodic_check,number_of_files,number_children;
char output_directory[BUFFER_SIZE];
char input_directory[BUFFER_SIZE];


output_directory=argv[2];
input_directory=argv[1];
periodic_check=atoi(argv[4]);
number_children=atoi(argv[3]);
pid_t pid[number_children];
int fd[number_children][2];
childrens_availability=(pid_t *) calloc(number_children,sizeof(pid_t));

for (int i = 0; i < number_children; i++)
{
  if(pipe(fd[i]) == -1){ 
    perror("Pipe failed"); 
    exit(1); 
  }
}


pid_handler=fork();

if(pid_handler==0){
  while(flagChildren==0){
    number_of_files=get_number_of_files(input_directory);
    sleep(periodic_check);
    int nfiles=get_number_of_files(input_directory);
    if(number_of_files<nfiles){
      kill(getppid(),SIGUSR1);
      pause();
      number_children=nfiles;
    }
  }
  exit(0);
}

for (int i = 0; i < number_children; i++)
{
  pid[i]=fork();
  if(pid[i]==0){
    while(flagChildren==0){
      close(fd[i][1]);
      char name[BUFFER_SIZE];
      while((n=read(fd[0],name,sizeof(BUFFER_SIZE)))!=0){
        //criar pasta para o candidato no output directory
        //copiar os ficheiros para o directory criado
      }
      kill(getppid(),SIGUSR2);
    }
    exit(0);
  }
}

pause();
while(flag==0){
  //verificar disponibilidade dos filhos e distribuir os candidato pelos filhos

}

for (int i = 0; i < number_children; i++)
{
  kill(pid[i],SIGKILL);
  waitpid(pid[i]);
}

return 0;

}