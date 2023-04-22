@echo off
title ARXML Sorter - Compile

echo The following command will build the project, it should create a new folder within build\classes
pause
javac -cp lib\\javafx.base.jar; -d build\\classes src\\*.java
echo: & echo:

echo The following command will create the new .jar excecutable.
echo WARNING: jar.exe must exist in the PATH variable (C:\Program Files\Java\jdkX.X\bin\jar.exe), add it to PATH or else the following command will cause an error.
echo WARNING: This command will overwrite the current ARXMLSorter.jar file, if you need it, rename it or copy it elsewhere. This cannot be undone. & echo:
pause
jar cvfm ARXMLSorter.jar build\\manifest.txt -C build\\classes . 
echo: & echo:

pause