#!/usr/bin/env bash

ECHO "Running Backoffice App..."
export BACKOFFICE=jobs4u.backoffice.app\target\jobs4u.backoffice.app-0.1.0.jar;jobs4u.backoffice.app\target\dependency\*

java -cp $BACKOFFICE backoffice.Jobs4uBackOffice