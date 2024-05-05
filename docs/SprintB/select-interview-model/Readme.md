# 1002 - As Customer Manager, I want to register a job opening.

--------

## 1.1. User Story Description

As Customer Manager, I want to register a job opening.

## 1.2. Customer Specifications and Clarifications

### From the specifications document:

      Job vacancies (job openings) must include a Job Requirement Specification. This represents a set of application requirements that the applicants must achieve. For instance, we could define a job requirement specification named "front end junior programmer", were candidates must have at least 2 years of experience, a degree in computer science or similar program, and knowledge in, at least, one of the following programming languages: java, javascript, typescript. Usually this information can be collected from the curriculum vitae of the candidate.

### From the client clarifications:


## 1.3. Acceptance Criteria

* The system must be able to:

  * display the modes available
  * display the contract type available
  * display companies


## 1.4. Found out Dependencies

* 1002 - As Customer Manager, I want to register a job opening.

## 1.5 Input and Output Data

* Input Data:

      - Selection of Job Opening.
      - Selection of Requirement Specification.
      - Data Confirmation.

* Output Data:

      - List of all job openings registered in the system
      - List of all requirement specification registered in the system
      - Chosen details
      - (In)Success of the operation

## 1.6. System Sequence Diagram (SSD)

![1002-system-sequence-diagram.svg](1002-system-sequence-diagram.svg)

## 1.7. System Diagram (SD)

![1002-sequence-diagram-overview.svg](1002-sequence-diagram-overview.svg)

## 1.8 Other Relevant Remarks

None to specify

## 2.0. Domain Model

----------------

### 2.1. Relevant Domain Model Excerpt

![1002-domain-model.svg](1009-domain-model.svg)

### 2.2. Entities and Aggregates

Entities represent distinct objects with unique identities and lifecycles within the domain. Aggregates are clusters of associated entities and value objects that are treated as a unit for data changes.

* Job Opening

      Represents job openings created by customers.

* JobRequirementsSpecification

      Represents a set of application requirements that candidates must meet for a specific job opening. These specifications are designed and implemented by Language Engineers and used in the candidate screening process.

* CustomerManager:

      Represents employees of the Jobs4U company who manage relationships with customer entities. Customer Managers are responsible for registering job openings, setting up recruitment processes, and overseeing the selection of candidates for their assigned customers.
* User

        Represents all the users that are registered in the system.

* Process

        Represents the different stages in which a job offer can be found.

### Associations


## 3.0. Class Diagram

------------------

### 3.1. Relevant Class Diagram Excerpt

![1002-class-diagram.svg](1002-class-diagram.svg)