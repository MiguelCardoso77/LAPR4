@ECHO OFF
ECHO Generating coverage report...

mvn clean org.jacoco:jacoco-maven-plugin:0.8.8:prepare-agent  verify org.jacoco:jacoco-maven-plugin:0.8.8:report

ECHO Check jobs4u.util.ci/target/site/jacoco-aggregate for the report