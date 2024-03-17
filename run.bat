@echo off
ECHO Verifying if java is correctly installed and set

java -jar jobs4u.jar

if %errorlevel% neq 0 (
    echo Error: %errorlevel%.
)

exit /b %errorlevel%