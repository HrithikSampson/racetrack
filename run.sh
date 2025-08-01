#!/bin/bash

export JAVA_HOME=/opt/homebrew/opt/openjdk@11
export PATH="/opt/homebrew/opt/openjdk@11/bin:$PATH"

./gradlew clean build -x test --no-daemon
java -jar build/libs/geektrust.jar < sample_input/input1.txt