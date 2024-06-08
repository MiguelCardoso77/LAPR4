# Get an ordered list of candidates, using the job interview points

--------

## 1. Requirements Engineering

### 1.1. User Story Description

As Customer Manager, I want to get an ordered list of candidates, using the job interview points (grades), to help me analyze the candidates.

### 1.2. Customer Specifications and Clarifications

**From the client clarifications:**

> **Question 169** 
> 
> Relativamente a esta user story, "US 1019 - As Customer Manager, I want to get an ordered list of 
> candidates, using the job interview points (grades), to help me analyze the candidates.", a lista que pretende é 
> relacionada a uma job opening correto? A maneira de ordenação é ascendente ou quer uma opção que inclua ascendente e descendente?
>
> **Answer**
> 
> Sim, a ordenação é relativa a candidaturas para um job opening. A ordenação deve ser descendente, do que tem maior pontuação 
> para o que tem menor pontuação.


> **Question 197**
> 
> Na questao Q169 é mencionado para a listagem ser ordenada descendentemente da nota da entrevista (como mencionado tambem na própria US)
> , no entanto, a questão é, como idealiza a ordenação caso a job opening não possua entrevista?
> 
> **Answer 197**
> 
> Esta US não faz sentido para processos que não tenham entrevista.

### 1.3. Acceptance Criteria

> **AC1:**
> The costumer manager should select the job opening for which he wants the list of candidates.

> **AC2:**
> The ordering must be descending, from the one with the highest score for the one with the lowest score.


### 1.4. Found out Dependencies

[Register a candidate](..%2F..%2FSprintB%2Fregister-a-candidate)

[Register a job opening](..%2F..%2FSprintB%2Fadd-jobOpening)

[Display candidate's data](..%2F..%2FSprintB%2Fdisplay-candidate's-data)

[List job openings' application](..%2F..%2FSprintB%2Flist-job-openings'-application)

[Job interviews evaluation](..%2F..%2FSprintB%2Fjob-interviews-evaluation)



### 1.5. Input and Output Data

**Selected Data:**

    * Job Opening

**Output Data:**

    * List of candidates ordered by score




## 2. Analysis and Design

### 2.1. Domain Model
![domain-model.svg](domain-model.svg)

### 2.2. Class Diagram
![class-diagram.svg](class-diagram.svg)

### 2.3. System Sequence Diagram (SSD)
![system-sequence-diagram.svg](system-sequence-diagram.svg)

### 2.4. Sequence Diagram (SD)
![sequence-diagram.svg](sequence-diagram.svg)


## 3. Implementation

The implementation of this user story was done in the UI layer, using some methods in  controllers, in the `OrderedListOfCandidatesUI` class. 
The `doShow` method was implemented to allow the customer manager to select a job opening and call all controllers used.
The `displayList` method was implemented to print the list that is the final result after 3 controllers used.
The `OrderedListOfCandidatesController` class is mainly used to order by grade all candidates that have an application for the selected job opening.

```java
    public boolean doShow() {

  JobOpening jobOpening = selectJobOpeningController.selectJobOpening();
  JobReference jobReference = jobOpening.jobReference();

  Iterable<Application> applicationList = listJobOpeningApplicationsController.allApplicationsOfJobOpening(jobReference);

  List<JobInterview> orderedList = orderedListOfCandidatesController.orderedList(applicationList);

  List<Application> finalList = orderedListOfCandidatesController.applicationList(orderedList);

  displayList(finalList, orderedList);


  return true;
}
```


## 4. Demonstration

    **job_opening_with_interviews**

![with_interview.png](with_interview.png)

    **job_opening_with_no_interviews**

![no_interview.png](no_interviews.png)





