#!/bin/bash

show_help() {
  echo "Usage: ./properties.sh [OPTION]"
  echo "  -c, --clean     Clean all application.properties files"
  echo "  -h, --help      Display this help and exit"
}

# Display help if --help or -h is provided
if [[ $1 == "--help" || $1 == "-h" ]]; then
  show_help
  exit 0
fi

# Use find to locate directories that match the pattern and contain application.example.properties
find . -type d -name "jobs4u.*" | while read -r dir; do
  resource_path="$dir/src/main/resources"
  example_properties="$resource_path/application.example.properties"
  actual_properties="$resource_path/application.properties"

  if [ -f "$example_properties" ]; then
    if [ -f "$actual_properties" ]; then
      if [[ $1 == "--clean" || $1 == "-c" ]]; then
        echo "> Removing $actual_properties"
        rm "$actual_properties"
        echo "> Creating $actual_properties"
        cp "$example_properties" "$actual_properties"
      else
        echo "> $actual_properties already exists. Skipping..."
      fi
    else
      echo "> Creating $actual_properties"
      cp "$example_properties" "$actual_properties"
    fi
  else
    echo "> $example_properties not found. Skipping $resource_path"
  fi
done