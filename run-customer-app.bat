@ECHO OFF

ECHO Running Customer App...
SET CUSTOMER=jobs4u.customer.app\target\jobs4u.customer.app-0.1.0.jar;jobs4u.customer.app\target\dependency\*

java -cp %CUSTOMER% user.console.CustomerApp
