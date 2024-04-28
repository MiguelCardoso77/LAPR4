#!/usr/bin/env bash

ECHO "Running Bootstrapp..."
export BOOTSTRAP=jobs4u.bootstrap\target\jobs4u.bootstrap-0.1.0.jar;jobs4u.bootstrap\target\dependency\*

java -cp $BOOTSTRAP bootstrap.Jobs4UBootstrap

