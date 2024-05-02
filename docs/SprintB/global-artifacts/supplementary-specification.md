# Supplementary Specification (FURPS+)

## Functionality

_Specifies the essential functionalities of the system, based on the requirements of the Jobs4U project:_

- The system must allow registration and management of clients.
- Clients should be able to register and manage job postings.
- Candidates must be able to apply and manage their applications.
- The system should automate the processing of applications received via email.
- System operators must monitor and manage the automated processing of applications.
- The system must allow integration of plugins for requirement specifications and interview models.
- Plugins should be capable of automating job requirement verification and interview assessment.

## Usability

_Evaluates the user interface and user experience, ensuring the system is intuitive and easy to use:_

- The system interface should be intuitive for clients, candidates, and operators.
- The system should provide feedback after each operation.
- The system should include a clear and comprehensive user manual.

## Reliability

_Specifies the system reliability requirements, ensuring it is robust and secure:_

- The system must be continuously available to all users.
- The system should be resilient to failures and data loss.
- The system should be able to handle multiple users simultaneously without performance degradation.

## Performance

_Evaluates the system performance requirements, ensuring it is responsive and efficient:_

- The system must provide fast response times for data searches and retrieval.
- The system should be able to handle large volumes of data and users.

## Supportability

_Addresses the system's maintainability and testability, ensuring it is easy to maintain and update:_

- The system should be easily maintainable and updatable.
- The system should provide useful error messages.
- The system should be testable, with comprehensive unit testing implementation.
- System documentation should be generated using Javadoc.

## Project Constraints

_Specifies the design, implementation, and interface constraints of the system:_

- Before running the Backoffice, the user must open the H2 database, run the Bootstrap and then the Backoffice
- The system must be developed in Java using the IntelliJ IDE.
- Development should follow agile methodologies with continuous integration.
- The system should be compatible with different devices and offer a simple and user-friendly interface.
- The project must have scripts to build, execute, deploy... effortlessly.