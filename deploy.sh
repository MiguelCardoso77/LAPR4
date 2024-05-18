#!/usr/bin/env bash

export JOBS4U_BOOTSTRAP=jobs4u.bootstrap/target/jobs4u.bootstrap-0.1.0.jar:jobs4u.bootstrap/target/dependency/*;

java -cp $JOBS4U_BOOTSTRAP bootstrap.Jobs4UBootstrap

export JOBS4U_CP=jobs4u.core/target/core-1.0.0.jar:jobs4u.core/target/dependency/*;

setsid java -Djavax.net.ssl.keyStore=serverkeystore.jks \
    -Djavax.net.ssl.keyStorePassword=password \
    -Djavax.net.ssl.trustStore=clienttruststore.jks \
    -Djavax.net.ssl.trustStorePassword=password \
     -cp $JOBS4U_CP core.application.controllers.App 2>/dev/null 1>&2 &

exit 0