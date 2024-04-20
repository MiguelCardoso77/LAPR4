# G007 -  Registering a candidate and creating a corresponding user.

## 1. Requirements Engineering

### 1.1. User Story Description

As Operator, I want to register a candidate and create a corresponding user.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

**From the client clarifications:**

> **Question 19:**
> 
> Na criação de um utilizador no sistema o nome é definido pelo utilizador ou é o nome da pessoa (primeiro e último) e se 
a password é definida pelo utilizador ou gerada pelo sistema?
>
> **Answer:**
> No âmbito da US 2000a o Operator cria utilizadores do sistema para candidatos que ainda não estejam no sistema. Tem de 
fazer isso com base nos dados recebidos na candidatura (que incluem email e nome). O email servirá para identificar a 
pessoa. Neste contexto é necessário ter uma password para esse novo utilizador. Uma vez que essa informação não é 
transmitida pelo candidato, suponho que a solução mais “aconselhada” será o sistema gerar uma password para esse utilizador. 
Como o utilizador/candidato irá receber essa informação (a forma de autenticação na app) está out of scope, no sentido em 
que não existe nenhuma US que remete para isso. As US 1000 e 1001 também remetem para criação de utilizadores. Aqui, 
eventualmente poderia-se pensar em introduzir manualmente as passwords, mas pode ser pelo mesmo mecanismo de definição 
automática de password, descrito anteriormente. Relativamente ao nome ver novamente a Q11.

### 1.3. Acceptance Criteria

### 1.4. Found out Dependencies

### 1.5 Input and Output Data

**Input Data:**

    * Selection of Job Opening.
    * Selection of Requirement Specification.
    * Data Confirmation.

**Output Data:**

    * List of all job openings registered in the system
    * List of all requirement specification registered in the system
    * Chosen details
    * (In)Success of the operation

### 1.6. System Sequence Diagrams (SSD)

### 1.7 Other Relevant Remarks

*  None to specify 