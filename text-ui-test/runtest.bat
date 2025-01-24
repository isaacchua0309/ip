@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from the previous run
del ACTUAL.TXT

REM Compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program, feed commands from input.txt, and redirect output to ACTUAL.TXT
java -classpath ..\bin Boblet < input.txt > ACTUAL.TXT

REM Compare actual output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
