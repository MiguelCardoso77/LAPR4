#!/usr/bin/env bash
ECHO "Running Bootstrapp..."
java -jar jobs4u.bootstrap/target/jobs4u.bootstrap-0.1.0.jar

ECHO "Running Customer App..."
java -jar jobs4u.customer.app/target/jobs4u.customer.app-0.1.0.jar

ECHO "Running Candidate App..."
java -jar jobs4u.candidate.app/target/jobs4u.candidate.app-0.1.0.jar

ECHO "Running Backoffice App..."
java -jar jobs4u.backoffice.app/target/jobs4u.backoffice.app-0.1.0.jar