#!/usr/bin/env bash

echo "Running the Customer App..."
java -jar jobs4u.customer.app/target/jobs4u.customer.app-0.1.0.jar

echo "Running the Candidate App..."
java -jar jobs4u.candidate.app/target/jobs4u.candidate.app-0.1.0.jar

echo "Running the Backoffice App..."