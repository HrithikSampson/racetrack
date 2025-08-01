#!/bin/bash

echo "Building Race Track Booking System..."

# Create build directory
mkdir -p build/classes

# Compile all Java files
echo "Compiling domain classes..."
javac -d build/classes -cp . src/main/java/com/example/geektrust/domain/*.java

echo "Compiling repository classes..."
javac -d build/classes -cp build/classes src/main/java/com/example/geektrust/repository/*.java

echo "Compiling service classes..."
javac -d build/classes -cp build/classes src/main/java/com/example/geektrust/service/*.java

echo "Compiling strategy classes..."
javac -d build/classes -cp build/classes src/main/java/com/example/geektrust/strategy/*.java

echo "Compiling use case classes..."
javac -d build/classes -cp build/classes src/main/java/com/example/geektrust/usecase/*.java

echo "Compiling controller classes..."
javac -d build/classes -cp build/classes src/main/java/com/example/geektrust/controller/*.java

echo "Compiling config classes..."
javac -d build/classes -cp build/classes src/main/java/com/example/geektrust/config/*.java

echo "Compiling main class..."
javac -d build/classes -cp build/classes src/main/java/com/example/geektrust/Main.java

echo "Build completed successfully!"
echo ""
echo "To run the application:"
echo "  java -cp build/classes com.example.geektrust.Main"
echo ""
echo "To test with input file:"
echo "  java -cp build/classes com.example.geektrust.Main < sample_input/input1.txt"
