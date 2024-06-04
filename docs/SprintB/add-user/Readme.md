# 1000 - be able to register, disable/enable, and list users of the backoffice.
--------

## 1.1. User Story Description

As Administrator, I want to be able to register, disable/enable, and list users of the backoffice.

## 1.2. Customer Specifications and Clarifications

### From the client clarifications:

## 1.3. Acceptance Criteria

## 1.4. Found out Dependencies

* 1002 - As Customer Manager, I want to register a job opening.

## 1.5 Input and Output Data

* Input Data:

      - Selection of user

* Output Data:

      - Chosen details
      - (In)Success of the operation

## 1.6. System Sequence Diagram (SSD)

![system-sequence-diagram.svg](system-sequence-diagram.svg)

## 1.7. Sequence Diagram (SD)

![sequence-diagram-overview.svg](sequence-diagram-addUser.svg)

## 1.8 Other Relevant Remarks

None to specify

## 2.0. Domain Model

----------------

### 2.1. Relevant Domain Model Excerpt

![domain-model.svg](domain-model.svg)

### 2.2. Entities and Aggregates

Entities represent distinct objects with unique identities and lifecycles within the domain. Aggregates are clusters of associated entities and value objects that are treated as a unit for data changes.

* Admin:

      Represents employees of the Jobs4U company who manage relationships with customer entities. Customer Managers are responsible for registering job openings, setting up recruitment processes, and overseeing the selection of candidates for their assigned customers.
* User

        Represents all the users that are registered in the system.

## 3.0. Class Diagram

------------------

### 3.1. Relevant Class Diagram Excerpt

![1000-class-diagram.svg](class-diagram.svg)