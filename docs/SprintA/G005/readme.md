# Deployment and Scripts - G005

This document describes the steps to execute the scripts so that build/execute/deploy/... can be executed effortlessly.

## 1. How to Build

To build the Jobs4U program, follow these steps:

a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
b) Navigate to the root directory of the project in the terminal.
c) Execute the one of the scripts (currently available for Linux/Unix/MacOS system):

    ./rebuild-all.bat
            or
    ./rebuild-all.sh

## 2. How to Execute Tests

To execute the project Jobs4U tests, follow these steps:

a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
b) Navigate to the root directory of the project in the terminal.
c) Execute the one of the scripts (currently available for Linux/Unix/MacOS system):

    ./run-tests.sh
            or
    ./run-tests.bat


## 3. How to Run

To run the Jobs4U program, follow these steps:

a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
b) Navigate to the root directory of the project in the terminal.
c) Execute the script (currently available for Linux/Unix/MacOS system):

    ./run.sh
        or
    ./run.bat

## 4. How to Install/Deploy into Another Machine (or Virtual Machine)

To install/deploy the Jobs4U program, follow these steps:

a) Ensure that Maven is installed on your system. If not, you can install it by following the instructions at https://maven.apache.org/install.html.
b) Navigate to the root directory of the project in the terminal.
c) Execute the script (currently available for Linux/Unix/MacOS system):

    ./deploy.sh
        or
    ./deploy.bat

## 5. How to Generate PlantUML Diagrams

To generate plantuml diagrams for documentation execute the script (for the moment, only for linux/unix/macos):

    ./generate-plantuml-diagrams.sh
        or
    ./generate-plantuml-diagrams.bat