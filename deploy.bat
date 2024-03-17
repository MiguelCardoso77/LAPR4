@echo off
setlocal

set "program_name="
set "jar_file="
set "deploy_directory="
set "log_file="

:log_message
echo [%date% %time%] %*
exit /b

call :log_message Starting the implementation of %program_name%

where java >nul 2>nul
if %errorlevel% neq 0 (
    call :log_message Error: Java isn´t installed. Please, download Java and try again!.
    exit /b 1
)

if not exist "%jar_file%" (
    call :log_message Error: JAR file '%jar_file%' not founded!
    exit /b 1
)

if not exist "%deploy_directory%" (
    mkdir "%deploy_directory%"
    call :log_message Creating the implementation directory : %deploy_directory%
)

copy "%jar_file%" "%deploy_directory%" >nul
if errorlevel 1 (
    call :log_message Error copying %jar_file% to %deploy_directory%
    exit /b 1
)
call :log_message Copying %jar_file% to %deploy_directory%

cd /d "%deploy_directory%" || (
    call :log_message Error changing the implementation directory: %deploy_directory%
    exit /b 1
)
call :log_message Directory changed to %deploy_directory%

call :log_message Starting %program_name%
start "" java -jar "%jar_file%" > "%log_file%" 2>&1

call :log_message Implementation of %program_name% concluded with success!
exit /b 0