# Continuous Integration (CI) Server - G004

This document describes the steps of the Continuous Integration (CI) server being used with GitHub Actions.

## GitHub Actions/Workflows Steps

1. **Step 1:**
    - Checkout code.
    - using: *actions/checkout@v4*

2. **Step 2:**
    - Set up JDK 17.
    - using: *actions/setup-java@v4*

3. **Step 3:**
    - Build with Maven and run tests with coverage for jobs4u.app1.
    - using: *mvn -B clean verify jacoco:report -f jobs4u.app1/pom.xml*

4. **Step 4:**
    - Build with Maven and run tests with coverage for jobs4u.app2.
    - using: *mvn -B clean verify jacoco:report -f jobs4u.app2/pom.xml*

5. **Step 5:**
    - Upload code coverage report for jobs4u.app1.
    - using: *actions/upload-artifact@v4*

6. **Step 6:**
    - Upload code coverage report for jobs4u.app2.
    - using: *actions/upload-artifact@v4*

7. **Step 7:**
    - Update dependency graph.
    - using: *advanced-security/maven-dependency-submission-action@v4*

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
