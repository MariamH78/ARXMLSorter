@echo off
title ARXML Sorter - Test

:loop
echo After pressing the key, the sorter will be automatically run against the four default test files in the test_files directory, as well as an extra non-existent file to test exceptions. & echo: & echo     1- test1.arxml: Sample input file, should run normally. & echo     2- test2.arxml: Empty file, will throw an exception. & echo     3- test3.xml: Wrong file extension, will throw an exception. & echo     4- test4.arxml: Tests nested containers, having multiple params for a tag, self closing tags and tags having random content in-between other tags, should run normally. & echo     5- test5.arxml: A file that doesn't exist, will throw an exception. & echo: & echo Output files will be written to the same directory. & echo: 
pause
echo Running..
java -jar ARXMLSorter.jar test_files\\test1.arxml test_files\\test2.arxml test_files\\test3.xml test_files\\test4.arxml test_files\\test5.arxml                                 

echo & echo: & echo: & echo To try your own files, run the following line from command terminal: & echo: & echo java -jar ARXMLSorter.jar path\\to\\test\\file.arxml another\\file\\here.arxml & echo: & echo There's no cap on number of inputs, and be careful to correctly type the path names. To run files from test_files folder, path and file name should be as follows: test_files\\your_file.arxml (Note the extra slash to escape the actual slash.) & echo: & echo: 

set /p "choice=Do you want to try again? (y/n): "

if "%choice%"=="y" (
	echo: & echo: 
    goto loop
)
	
echo: & echo Have a good day! & echo:
pause