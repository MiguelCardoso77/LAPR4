#!/bin/bash

echo "Building the Maven project..."

if ! command -v mvn &> /dev/null; then
    echo "Maven isn´t installed!"
    exit 1
fi

echo "Building the source code..."
mvn compile

if [ $? -ne 0 ]; then
    echo "The compilation has failed"
    exit 1
fi

echo "Executing the unit tests..."
mvn test
if [ $? -ne 0 ]; then
    echo "Failure in the execution of the unit tests"
    exit 1
fi

echo "Successfully build!"