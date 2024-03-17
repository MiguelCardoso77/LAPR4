#!/usr/bin/env bash

program_name = ""
jar_file = ""
deploy_directory = ""
log_file = ""

log_message(){
  ECHO [ date + %Y-%m-%d %H:%M:%S ] $1
}

log_message "Starting deployment of $program_name"

if ! command -v java &> /dev/null; then
    log_message "Error: Java is not installed. Please install Java and try again."
    exit 1
fi

if [ ! -f "$jar_file" ]; then
    log_message "Error: JAR file '$jar_file' not found."
    exit 1
fi

if [ ! -d "$deploy_directory" ]; then
    mkdir -p "$deploy_directory"
    log_message "Created deploy directory: $deploy_directory"
fi

cp "$jar_file" "$deploy_directory"
log_message "Copied $jar_file to $deploy_directory"

cd "$deploy_directory" || exit
log_message "Changed directory to $deploy_directory"

log_message "Starting $program_name"
java -jar "$jar_file" > "$log_file" 2>&1 &

log_message "Deployment of $program_name completed successfully!"