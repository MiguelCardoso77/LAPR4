# Supplementary Specification (FURPS+)

## Functionality

**1. Language Support:** The application must support the English language as the primary language for user interaction and system messages. Language support should be extendable to accommodate additional languages in future iterations if required.

**2. Image Format:** All images used within the application interface or for documentation purposes must be in Scalable Vector Graphics (SVG) format. This ensures scalability, high quality, and compatibility across different devices and screen resolutions.

**3. Audit Trail:** The application should maintain an audit trail to track significant events and actions performed within the system. This includes user activities, system changes, and data modifications. Audit logs should include timestamps, user identifiers, and details of the action performed.**

**4. Reporting:** Access to the application requires authentication with a password comprising eight alphanumeric characters, including one capital letter and three digits.


## Usability

**1. Consistency:** Ensure coherence throughout all interface elements and functionalities, fostering a seamless user experience across the application's various screens and features.

**2. Assistance and Documentation::** Supportive Guidance and Documentation: Deliver extensive assistance materials and documentation to empower users in maximizing software utilization efficiency.

**3. Design:** Secure an interface that not only entices with visual allure but also seamlessly guides users with intuitive interaction, while staying in step with cutting-edge design paradigms.

**4. Error Prevention:** To deter incorrect or invalid data input, integrate  validation mechanisms within the user interface.


## Reliability

**1. Fault Tolerance:** Implement strategies to enhance system resilience, minimizing the impact of failures and ensuring continuous operation.

**2. Recovery Protocols:** Establish mechanisms for seamless recovery from errors, including robust data backup procedures and rollback mechanisms.

**3. Proactive Maintenance:** Integrate advanced monitoring tools to predict and prevent potential failures, ensuring optimal system performance.

**4. Data Integrity:** Prioritize the accuracy and integrity of data, safeguarding against errors and maintaining trustworthiness.

**5. Reliability Duration:** Maintain prolonged periods of system reliability by minimizing downtime and enhancing overall operational continuity.

## Performance

**1. Responsiveness:** Prioritize quick system responses to user interactions, ensuring a seamless and efficient user experience.

**2. Initialization Speed:** Strive for rapid start-up times to minimize user waiting periods and expedite access to the software.

**3. Restoration Time:** Enable swift recovery from failures to restore normal operation promptly and minimize downtime.

**4. Resource Management:** Efficiently allocate and manage memory and CPU resources to optimize overall system performance.

**5. Scalability:** Capably handle high volumes of concurrent users and data processing demands without sacrificing performance.

**6. Accessibility:** Maintain consistent availability of the application to ensure uninterrupted access for users at all times.

## Supportability

**1. Testability:** Implement unit tests for all methods except those handling Input/Output operations, utilizing the JUnit 5 framework and JaCoCo plugin for coverage report generation.

**2. Adaptability:** Guarantee the system's capability to seamlessly adjust to evolving requirements, technological advancements, and changing environments.

**3. Maintainability:** Employ object serialization for data persistence, facilitating the retention of user data and system state across multiple sessions.

**4. Configurability:** Enable extensive customization of the system to meet diverse user needs and preferences, enhancing flexibility and usability.

**5. Installability:** Ensure straightforward installation and deployment across various environments to facilitate ease of use.

**6. Scalability:** Design the system to effortlessly accommodate increases in data volume, user base, and functionality, ensuring sustained performance.

## +

### Design Constraints

**1. Development Methodology:** Embrace agile methodologies like Scrum for effective project management and adaptability.

**2. Coding Conventions:** Uphold recognized coding standards such as CamelCase for enhanced code readability and consistency.

**3. Development Environment:** IntelliJ IDEA or NetBeans as the primary development tool for Java projects.

**4. Programming Language:** Employ Java as the primary language for implementing the system, leveraging its robust features and wide adoption.

**5. Best Practices:** Adhere to industry standards and patterns for requirement analysis and object-oriented software design to ensure high-quality outcomes.

**6. Documentation Tools:** Utilize Javadoc to generate comprehensive documentation for Java code, facilitating understanding and maintenance.

### Implementation Constraints

**1. Coding Practices and Design Patterns:** Follow established industry norms and design patterns to ensure robustness and maintainability of the codebase.

**2. Language Implementation:** Choose Java as the primary implementation language for its versatility and widespread adoption.

**3. Data Security:** Guarantee data integrity by implementing sound database design principles and enforcing stringent constraints.

**4. Efficient Resource Management:** Employ strategies to efficiently manage system resources, mitigating the risk of performance bottlenecks.

**5. Cross-Platform Compatibility:** Ensure seamless operation across major operating systems including Windows, macOS, and Linux, enhancing accessibility for users.

### Interface Constraints

User Interface Framework: Utilize JavaFX 11 for the development of the application's graphical interface, leveraging its advanced features and capabilities.

### Physical Constraints

No explicit physical limitations have been outlined within the provided details.