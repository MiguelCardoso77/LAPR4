@ECHO OFF

ECHO Running Follow-Up Server...
SET SERVER=jobs4u.follow.up.server\target\jobs4u.follow.up.server-0.1.0.jar;jobs4u.follow.up.server\target\dependency\*

java -cp %SERVER% followUp.FollowUpServer