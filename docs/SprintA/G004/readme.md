# G004 - Continuous Integration (CI) Server

This document describes the steps of the Continuous Integration (CI) server being used with GitHub Actions.

## GitHub Actions/Workflows Steps

- **Step 1:**
    - Checkout code.
    - using: *actions/checkout@v4*

- **Step 2:**
    - Set up JDK 17.
    - using: *actions/setup-java@v4*

- **Step 3:**
    - Build with Maven and run tests with coverage for all apps.
    - using: *mvn -B clean verify jacoco:report -f jobs4u.candidate.app/pom.xml*
    - using: *mvn -B clean verify jacoco:report -f jobs4u.customer.app/pom.xml*
    - using: *mvn -B clean verify jacoco:report -f jobs4u.backoffice.app/pom.xml*

- **Step 4:**
    - Upload code coverage report for all apps.
    - using: *actions/upload-artifact@v4*

## Repository Structure

- **.github:**
    - Contains GitHub Actions/Workflows configuration files.

- **docs:**
    - Technical documentation of the project, including CI steps instructions.

## Maintenance and Updates

- Keep documentation updated as new CI/CD requirements arise and the project evolves.
- Conduct periodic reviews of CI workflows to ensure their effectiveness and efficiency.

## Additional Resources

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
