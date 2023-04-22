# ARXMLSorter
This repository contains my solution for lab 6 for [CSE231s - Advanced Computer Programming], scripts that sort .arxml files according to the value between the &lt;SHORT-NAME> tags.

Running this package takes a `file_name.arxml` file, and produces a new modified `file_name_mod.arxml` with  re-ordered containers in the same directory.

## Steps to download this package:
- Download the repository as a zip file, or git clone from terminal:
    ```
    git clone https://github.com/MariamH78/ARXMLSorter.git
    ```
## Different options to run this package:
- To run the ARXMLSorter.jar executable against the default test cases, double click on `run.bat`.
- To run the ARXMLSorter.jar executable against your own test cases, type in the terminal:
    ```
    java -jar ARXMLSorter.jar path\\to\\test\\file.arxml another\\file\\here.arxml
    ```
- To recompile then run your new ARXMLSorter.jar executable against the default test cases, double click on `recompile_and_run.bat`.
- To compile and create a new ARXMLSorter.jar executable without running the default test cases, double click on `compile.bat`.

__Note: `compile.bat` and `recompile_and_run.bat` need `jar.exe` to be on environment's PATH variable.__

__`jar.exe` is found in `(C:\Program Files\Java\jdkX.X\bin\jar.exe)`.__

## Default test cases description:

1. `test1.arxml`: Sample input file. Should run normally. 
2. `test2.arxml`: Empty file. Will throw an exception.
3. `test3.xml`: Wrong file extension. Will throw an exception. 
4. `test4.arxml`: Tests nested containers, empty containers, having multiple params for a tag, spaces in a tag's param, self closing tags, tags having random content in-between other tags. Should run normally. 
5. `test5.arxml`: A file that doesn't exist. Will throw an exception.