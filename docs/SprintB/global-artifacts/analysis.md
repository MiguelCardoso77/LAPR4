# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development".


### _Conceptual Class Category List_

**Business Transactions**


* **Task assignment:** involves allocating tasks to collaborators or teams within the system. 
* **Team proposal:** generation entails creating proposals for team composition based on task requirements and the skills of collaborators. 
* **Vehicle check-up:** refers to the process of inspecting and maintaining vehicles used in the management of green spaces.
---

**Transaction Line Itemss**

* **Diary:** Functions as a log of scheduled tasks or activities to be carried out in green spaces, offering a chronological outline for work.
---

**Product/Service related to a Transaction or Transaction Line Item**

* **Equipment:** Refers to tools or machinery used in tasks. 
* **Vehicle:** Denotes vehicles employed for transportation or tasks. 
* **Machine:** Represents machinery used for various tasks. 
* **Automotive Inspection:** Appears incomplete and necessitates additional clarification or refinement.

---

**Transaction Records**

* Not explicitly represented in the provided model.

---  

**Roles of People or Organizations**

* **Collaborator:** Signifies an employee engaged in tasks related to managing green spaces.
* **HumanResourcesManager:** Denotes the individual accountable for overseeing human resources.
* **FleetManager:** Represents the individual responsible for managing the fleet of vehicles and equipment.
* **GreenSpacesManager:** Represents the individual responsible for the comprehensive management of green spaces.
* **GreenSpacesUser:** Designates a user of the green spaces managed by the organization.
---

**Places**

* Garden
* Medium-sized Parks
* Large-sized parks.

---

**Noteworthy Events**

* **Task:** Denotes tasks or activities to be executed within the domain.
* **PortalUser:** Refers to faults or malfunctions reported by users in the User Portal.
---

**Physical Objects**

* **Equipment:** Denotes the equipment used by collaborators for tasks.
* **Vehicle:** Represents the vehicles employed for transportation or tasks.
* **Machine:** Signifies the machinery used for various tasks.
* **PlantMaterial:** Represents materials associated with plants.
* **UrbanFurniture:** Denotes urban furniture.
* **Infrastructure:** Signifies foundational structures or systems.
---

**Descriptions of Things**

* **Vehicle:** Includes Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Checkup Frequency (in Kms).
* **Skill:** Denotes skills possessed by individuals.
* **Job:** Represents roles or positions within the organization.

---

**Catalogs**
* **Catalog of vehicles:** Contains a comprehensive list of all vehicles owned or used by the organization for tasks related to managing green spaces, detailing a vehicle type, model, capacity, current condition, etc.
* **Catalog of equipment:** Encompasses a list of equipment used by collaborators for various tasks within green spaces management, including tools, machinery, and other resources necessary for effective task execution.
* **Catalog of jobs:** Includes a list of job roles or positions within the organization, providing descriptions of each role's responsibilities, qualifications, and required skills.
* **Catalog of skills:** Comprises a list of skills possessed by collaborators within the organization, outlining their various capabilities and competencies relevant for task assignment and team composition.
* **Catalog of machines:** Encompasses a list of machines used within the domain of green spaces management, detailing a machine type, model, specifications, and functionalities.
* **Catalog of PlantMaterial:** Contains a list of plant materials used within green spaces managed by the organization, including various types of plants, flowers, shrubs, trees, along with their characteristics and maintenance requirements.
* **Catalog of UrbanFurniture:** Includes a list of urban furniture or amenities present within green spaces managed by the organization, such as benches, tables, playground equipment, along with their specifications and locations.
* **Catalog of Infrastructure:** Comprises a list of infrastructure elements present within green spaces managed by the organization, encompassing foundational structures, systems, and facilities such as irrigation systems, lighting, drainage systems, etc.

---

**Containers**

* **Team:** Represents groups of individuals collaborating towards common goals.

---


**Elements of Containers**

* **Collaborator:** Represents a staff member/ employee engaged in tasks pertaining to the management of green spaces.
* 
---

**Organizations**

* **MusgoSublime (MS):** Represents the overarching entity that manages the domain

---

**Other External/Collaborating Systems**

* **UserPortal:** Refers to a system interface enabling users to engage with the organization, report faults, and perform other interactions.

---

**Records of finance, work, contracts, legal matters**

* **Work Records:** Documentation pertaining to work conducted within green spaces, encompassing task completion, maintenance, etc.
* **Maintenance Records:** Documentation of maintenance activities carried out on equipment, vehicles, etc.

---

**Financial Instruments**

* Not explicitly represented in the provided model.

---

**Documents mentioned/used to perform some work/**

* Not explicitly represented in the provided model.

---


## Rationale to identify associations between conceptual classes


| Concept (A) 		 |  Association   	   | Concept (B) |
|----------------|:------------------:|------------:|
| Diary  	       |    has    		 	     |       Entry |
| Entry  	       |  defines    		 	   |        Task |
| Entry  	       |  located    		 	   |  GreenSpace |
| Job  	         |    has    		 	     |        Task |
| Task  	        |  requires    		 	  |       Skill |
| Task  	        |   needs    		 	    |     Vehicle |
| Task  	        |   needs    		 	    |    Machines |
| Task  	        |   needs    		 	    |   Equipment |
| Team  	        |  performs    		 	  |        Task |
| User  	        |    has    		 	     |    UserType |
| User  	        |   sends    		 	    |    Feedback |
| User  	        |    uses    		 	    |  UserPortal |
| User  	        | belongs to    		 	 |        Team |
| User  	        |   has a     		 	   |         Job |
| User  	        |    has    		 	     |       Skill |
| UserPortal  	  |  receives    		 	  |    Feedback |
| Vehicle  	     | transports    		 	 |    Machines |
| Vehicle  	     | transports    		 	 |   Equipment |


## Domain Model

![Domain Model](svg/project-domain-model.svg)