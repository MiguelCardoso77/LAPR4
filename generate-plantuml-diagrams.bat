@echo off
echo LOG: Generate Plantuml Diagrams
set exportFormat=svg
set extra=SdefaultFontSIze=20

for /r docs %%i in (*.puml) do (
    echo Processing file : %%i
    java -jar libs\plantuml-1.2023.1.jar %extra% -t%exportFormat% "%%i"
)

echo Finished!