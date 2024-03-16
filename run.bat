@echo off
ECHO Verifying if java is correctly installed and set
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed!
    exit /b 1
)

java -jar jobs4u-0.1.0.jar

set exit_code=%errorlevel%

if %exit_code% neq 0 (
    echo Error: %exit_code%.
    exit /b %exit_code%
)

echo Execution completed successfully.
exit /b 0