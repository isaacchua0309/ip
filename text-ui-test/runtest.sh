#!/bin/bash

# Create bin directory if it doesn't exist
mkdir -p ../bin

# Delete output from the previous run
rm -f ACTUAL.TXT

# Compile the code into the bin folder
javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
if [ $? -ne 0 ]; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program, feed commands from input.txt, and redirect output to ACTUAL.TXT
java -classpath ../bin Boblet < input.txt > ACTUAL.TXT

# Compare actual output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]; then
    echo "Test passed!"
else
    echo "Test failed. See differences above."
fi
