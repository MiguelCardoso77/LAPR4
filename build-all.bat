ECHO OFF
ECHO Make sure JAVA_HOME is set to JDK folder
ECHO Make sure maven is on the system PATH
mvn %1 dependency:copy-dependencies package