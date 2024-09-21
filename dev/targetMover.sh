#!/bin/bash

# Check if the destination folder is provided
if [ -z "$1" ]; then
  echo "Usage: $0 <destination-folder>"
  exit 1
fi

DESTINATION_FOLDER=$1

# Run Gradle to build the project
./gradlew build

# Check if the build was successful
if [ $? -ne 0 ]; then
  echo "Gradle build failed"
  exit 1
fi

# Find the JAR file (you can adjust the path based on your project structure)
JAR_FILE=$(find build/libs -name "*.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
  echo "No JAR file found"
  exit 1
fi

# Copy the JAR file to the specified destination folder
cp "$JAR_FILE" "$DESTINATION_FOLDER"

# Check if the copy was successful
if [ $? -eq 0 ]; then
  echo "JAR file successfully copied to $DESTINATION_FOLDER"
else
  echo "Failed to copy JAR file"
  exit 1
fi
