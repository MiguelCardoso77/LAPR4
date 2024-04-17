# G001 - Following technical constraints

### NFR01 - Programming Language
- **Constraint 1:**
    - *Description:* The solution must be implemented primarily using Java.
    - *Action Item:* Ensure that all core functionalities are developed in Java, with consideration for specific requirements that may necessitate the use of other languages.

### NFR02 - Technical Documentation
- **Constraint 2:**
    - *Description:* Project documentation must be maintained in the project repository's "docs" folder in Markdown format, following UML notation where applicable.
    - *Action Item:* Regularly update and expand project documentation to include development processes for each user story, adhering to UML notation standards.

### NFR03 - Test-driven Development
- **Constraint 3:**
    - *Description:* The team should adopt a test-driven development approach, creating automated tests for every user story, class, and method.
    - *Action Item:* Develop a comprehensive suite of automated tests to ensure thorough test coverage for all aspects of the application.

### NFR04 - Source Control
- **Constraint 4:**
    - *Description:* Version control using Git/GitHub is mandatory, with only the main branch (e.g., master/main) being utilized for releases.
    - *Action Item:* Ensure all source code, documentation, and related artifacts are versioned in the GitHub repository, following best practices for branching and merging.

### NFR05 - Continuous Integration
- **Constraint 5:**
    - *Description:* Nightly builds with result and metrics publishing must be implemented using GitHub Actions.
    - *Action Item:* Configure GitHub Actions workflows to perform nightly builds, integrating automated testing and reporting of build metrics.

### NFR06 - Deployment and Scripts
- **Constraint 6:**
    - *Description:* The repository should include build and deployment scripts for various systems (Linux, Windows), along with a comprehensive README file explaining the build, deployment, and execution processes.
    - *Action Item:* Develop and maintain scripts for building, deploying, and running the solution on different platforms, ensuring ease of deployment and execution.

### NFR07 - Database
- **Constraint 7:**
    - *Description:* The system must support data persistence using either in-memory or relational database solutions, with a preference for a persistent relational database in the final deployment.
    - *Action Item:* Implement database configurations to support both in-memory and relational database persistence, with the ability to initialize default data.

### NFR08 - Authentication and Authorization
- **Constraint 8:**
    - *Description:* The system must support and enforce authentication and authorization for all users and functionalities.
    - *Action Item:* Implement robust authentication and authorization mechanisms to control access to system features and data.

### NFR09(LPROG) - Requirement Specifications and Interview Models
- **Constraint 9:**
    - *Description:* Support for this functionality must adhere to specific technical requirements specified in LPROG, utilizing the ANTLR tool.
    - *Action Item:* Ensure compliance with technical specifications provided by LPROG, incorporating ANTLR for requirement specifications and interview models.

### NFR10(RCOMP) - Client-Server Architecture
- **Constraint 10:**
    - *Description:* Functionalities related to the Candidate and Customer Apps and the Follow-Up Server must follow a client-server architecture, with specific communication protocols defined in the "Application Protocol" document from RCOMP.
    - *Action Item:* Implement client-server architecture, ensuring adherence to the specified communication protocols and restrictions on direct database access from client applications.

### NFR11(RCOMP) - Deployment in the Cloud
- **Constraint 11:**
    - *Description:* Deployment of the solution, especially the relational database server and the Follow-Up Server, should be done in cloud environments rather than locally.
    - *Action Item:* Configure deployment of relevant components in cloud environments, ensuring availability and scalability.

### NFR12(SCOMP) - File Upload Solution with C Programming Language
- **Constraint 12:**
    - *Description:* The base solution for file upload must be implemented using the C programming language with processes, signals, and pipes, as specified in SCOMP.
    - *Action Item:* Develop the file upload functionality using C programming language, incorporating processes, signals, and pipes as per SCOMP requirements.

### NFR13(SCOMP) - Alternative File Upload Solution with C Programming Language
- **Constraint 13:**
    - *Description:* An alternative solution for file upload must be implemented using the C programming language with shared memory and semaphores, as specified in SCOMP.
    - *Action Item:* Develop an alternative file upload solution using C programming language, utilizing shared memory and semaphores as specified in SCOMP.

### NFR14(SCOMP) - Word Count Process with Java and Threads
- **Constraint 14:**
    - *Description:* The process for counting words of large files must implement parallelism and concurrency using Java and threads, following specific technical requirements outlined in SCOMP.
    - *Action Item:* Implement the word count process using Java and threads, adhering to the parallelism and concurrency requirements specified in SCOMP.

### NFR15(LAPR4) - Presentation Requirements
- **Concern 1:**
    - *Description:* The project has specific requirements for communication and backoffice.presentation of project results, particularly in the context of sprint reviews for LAPR4 TP classes.
    - *Action Item:* Collaborate with LAPR4 to fulfill backoffice.presentation requirements and ensure alignment with project goals and objectives.
