ECHO OFF
ECHO make sure Java is installed and correctly configurated
java -jar jobs4u.jar

exit_code=$S?
if [ $exit_code -ne 0 ]; then
    echo "Error: $exit_code."
fi

exit $exit_code