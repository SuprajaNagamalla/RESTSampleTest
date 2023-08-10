Download JDK 7 version on Mac 64 bit
jdk-7u80-macosx-x64.dmg - check this on below page
https://www.oracle.com/in/java/technologies/javase/javase7-archive-downloads.html
=================================
Download JDK 7 version on Windows 64 bit using below path
jdk-7u80-windows-x64.exe - check this on below page
https://www.oracle.com/in/java/technologies/javase/javase7-archive-downloads.html
goto 
=================================
Download Maven 3.8.6 latest version for Windows
https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.8.6/

===========setting for Windows
set below system environment variables 

Setup JAVA_HOME
C:\Users\<username>\jdk-18.0.1.1\jdk-18.0.1.1

Setup MAVEN_HOME
C:\Program Files\apache-maven-3.8.6


then add them to PATH varaible as below
%JAVA_HOME%\bin
%MAVEN_HOME%\bin


==================To run the apple API tests using TestNG=========
go to path of application in command prompt
run below commands

mvn clean install test -Dsurefire.suiteXmlFiles=appleAPI.xml

or

--------------to set up clean mavent taget folder and re-isntall all dependencies-----
mvn clean install

--------------to set up clean mavent taget folder and run test methods only-----
mvn clean test

or 

--------------to set up clean mavent taget folder and re-isntall all dependencies and run test methods-----
mvn clean install test
