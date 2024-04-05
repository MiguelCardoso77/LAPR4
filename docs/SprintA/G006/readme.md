# Domain Model elaboration using DDD - G006

## Overview

This README contains the Domain Model for the Jobs4U project. The Domain Model represents the essential concepts, entities, aggregates, and their relationships within the project's domain.

## Contents

## Introduction

The domain model serves as a conceptual blueprint of the project's domain. It helps to understand the key entities, their attributes, and relationships, facilitating effective communication and development.

## Project Structure

The domain model is structured using UML notation. It consists of entities, aggregates, value objects, and associations between them.

## Entities and Aggregates

Entities represent distinct objects with unique identities and lifecycles within the domain. Aggregates are clusters of associated entities and value objects that are treated as a unit for data changes.


- Customer

      Represents entities (other companies or entities) that need to recruit human resources.

- Candidate

      Represents individuals applying for job openings.

- Job Opening

      Represents job openings created by customers.

- Application

      Represents job applications submitted by candidates.

- Job Interview

      Represents interviews conducted for job candidates.

## Value Objects

Value objects are immutable objects that represent attributes or characteristics within the domain. They do not have a unique identity and are defined by their attributes.

CustomerName

CustomerEmail

CustomerPhoneNumber

CustomerPassword

CandidateName

CandidateEmail

CandidatePhoneNumber

CandidatePassword

Curriculum

Requirements

JobReference

TitleOrFunction

ContractType

Mode

Address

Company

VacanciesNumber

Description

ProcessState

EmailContentFile

EmailFilesAttached

FilesAttachedContent

ApplicationDataFile

Status

SubmissionDate

InterviewModel

Results

InterviewTime


## Associations

Associations represent relationships between entities, aggregates, and value objects within the domain. They define how objects are connected and interact with each other.

Customer -> CustomerEmail

Customer -> CustomerName

Customer -> CustomerPhoneNumber

Customer -> CustomerPassword


Candidate -> CandidateEmail

Candidate -> CandidateName

Candidate -> CandidatePhoneNumber

Candidate -> CandidatePassword

Candidate -> Curriculum


Job Opening -> JobReference

Job Opening -> TitleOrFunction

Job Opening -> ContractType

Job Opening -> Mode

Job Opening -> Address

Job Opening -> Company

Job Opening -> VacanciesNumber

Job Opening -> Description

Job Opening --> JobRequirementsSpecifications

Job Opening --> Customer

Job Opening --> Application



Application -> EmailContentFile

Application -> EmailFilesAttached

Application -> FilesAttachedContent

Application -> ApplicationDataFile

Application -> Status

Application -> SubmissionDate

Application --> JobInterview

Application --> Candidate



Job Interview -> InterviewModel

Job Interview -> Results

Job Interview -> InterviewTime


Job Requirements Specifications --> Requirements


## Domain Model

![domain_model.svg](domain_model.svg)