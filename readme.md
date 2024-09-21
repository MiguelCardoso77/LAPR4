# Project Jobs4U

## 1. Description of the Project

Jobs4U is a company specialized in talent acquisition. The company provides recruitment services for job positions in its clients. The aim of this project is to develop, in an exploratory way,
a solution that allows automating the main activities of the company. Therefore, a minimum
viable product should be developed in 3 months.
The company’s clients are other companies or entities that need to recruit human resources.
In response to requests from its clients, Jobs4U develops all activities that allow it to select a
set of candidates for job offers (from its clients). At the end of the process, Jobs4U must deliver
to its client an ordered list of candidates for each job offer. The final recruitment decision is
the responsibility of the client.

## 2. Grades

Here are the grades obtained in the courses related to the development of this project:

- **LAPR4**: 18/20 — Graded on the overall project, including all features and final integration.
- **EAPLI**: 13/20 — Graded on documentation and testing.
- **LPROG**: 18/20 — Graded on ANTLR4 grammars and related features.
- **RCOMP**: 17/20 — Graded on the follow-up server and associated functionalities.
- **SCOMP**: 17/20 — Graded on file bots developed in C and Java.

## 3. Planning and Technical Documentation

[Planning and Technical Documentation](docs/readme.md)

## 4. How to Build

To build the Jobs4U program, follow these steps:

- a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
- b) Navigate to the root directory of the project in the terminal.
- c) Execute the one of the scripts (currently available for Linux/Unix/macOS system):


    ./rebuild-all.bat
            or
    ./rebuild-all.sh

## 5. How to Execute Tests

To execute the project Jobs4U tests, follow these steps:

- a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
- b) Navigate to the root directory of the project in the terminal.
- c) Execute the one of the scripts (currently available for Linux/Unix/macOS system):


    ./run-tests.sh
            or
    ./run-tests.bat

### 5.1. How to generate coverage report

- Execute the one of the scripts (currently available for Linux/Unix/macOS system):


    ./generate-coverage-report.sh
            or
    ./generate-coverage-report.bat

## 6. How to Run

To run the Jobs4U program, follow these steps:

- a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
- b) Navigate to the root directory of the project in the terminal.
- c) Execute the script (currently available for Linux/Unix/macOS system):


    ./run-all.sh
        or
    ./run-all.bat

### 6.1. How to Run the Customer app

To run the Customer app, follow these steps:

- a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
- b) Navigate to the root directory of the project in the terminal.
- c) Execute the script (currently available for Linux/Unix/macOS system):
    

    ./run-customer-app.sh
        or
    ./run-customer-app.bat

### 6.2. How to Run the Candidate app

To run the Candidate app, follow these steps:

- a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
- b) Navigate to the root directory of the project in the terminal.
- c) Execute the script (currently available for Linux/Unix/macOS system):


    ./run-candidate-app.sh
        or
    ./run-candidate-app.bat

### 6.3. How to Run the Backoffice app

To run the Candidate app, follow these steps:

- a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
- b) Navigate to the root directory of the project in the terminal.
- c) Execute the script (currently available for Linux/Unix/macOS system):


    ./run-backoffice-app.sh
        or
    ./run-backoffice-app.bat

### 6.4. How to Run the Follow-Up Server

To run the Follow-Up Server, follow these steps:

- a) Connect to the DEI VPN, instructions at https://rede.dei.isep.ipp.pt/usermanual/vpn.html
- b) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
- c) Navigate to the root directory of the project in the terminal.
- d) Execute the script (currently available for Linux/Unix/macOS system):


    ./run-follow-up-server.sh
        or
    ./run-follow-up-server.bat

## 7. How to Install/Deploy into Another Machine (or Virtual Machine)

To install/deploy the Jobs4U program, follow these steps:

- a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
- b) Navigate to the root directory of the project in the terminal.
- c) Execute the script (currently available for Linux/Unix/macOS system):


    ./deploy.sh
        or
    ./deploy.bat

## 8. How to Generate PlantUML Diagrams

To generate plantuml diagrams for documentation execute the script (for the moment, only for linux/unix/macos):

    ./generate-plantuml-diagrams.sh
        or
    ./generate-plantuml-diagrams.bat