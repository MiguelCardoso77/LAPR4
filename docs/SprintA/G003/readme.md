# G003 - Configuring project structure

This document outlines the configuration of the project structure to support the envisioned architecture and adopted technologies, as specified in Chapter 4 of the project requirements.

## Chapter 4: Project Structure Definition

- **Objective:** Configure the project structure to facilitate and accelerate the development of the Backoffice app, Candidate app, Customer app, Follow-Up Server, and associated plugins, as described in Chapter 4 of the project requirements.

- **Envisioned Architecture:**
    - The project will follow a modular architecture, with clear separation of concerns to promote scalability and maintainability.
    - Key components/modules will be identified and organized according to their functionalities, including the Backoffice app, Candidate app, Customer app, Follow-Up Server, and language automation plugins.

## Project Structure Guidelines:

1. **Module-based Structure:**
    - Divide the project into logical modules corresponding to the Backoffice app, Candidate app and Customer app.
    - Each module should encapsulate related features to promote cohesion and ease of development.

2. **Standardized Naming Conventions:**
    - Adopt standardized naming conventions for files, classes, and packages to enhance code readability and maintainability.

3. **Directory Organization:**
    - Organize directories and subdirectories in a hierarchical manner, reflecting the modular structure of the project and the dependencies between components.

4. **Documentation and Comments:**
    - Include comprehensive documentation and comments within the codebase to aid understanding and promote collaboration among team members.

## Installation of ANTLR:

    <!-- ANTLR -->
    <dependency>
        <groupId>org.antlr</groupId>
        <artifactId>antlr4-runtime</artifactId>
        <version>4.7.1</version>
    </dependency>

    <!-- ANTLR -->
    <plugin>
        <groupId>org.antlr</groupId>
        <artifactId>antlr4-maven-plugin</artifactId>
        <version>4.7.1</version>
        <executions>
            <execution>
                <goals>
                    <goal>antlr4</goal>
                </goals>
            </execution>
        </executions>
    </plugin>

- These lines of code are added to the `pom.xml` file to install ANTLR, a language automation tool that will be used to generate parsers for the language plugins.