#!/usr/bin/env bash

echo "Running Follow-Up Server..."
export CUSTOMER=jobs4u.follow.up.server\target\jobs4u.follow.up.server-0.1.0.jar;jobs4u.follow.up.server\target\dependency\*

java -cp $CUSTOMER followUp.FollowUpServer