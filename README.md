# ATM-Case-Study
PM Case Study 2 ATM

Please compile all the java files once before trying to run Test.java which contains the main method
The JDBC driver and the existing database with some preloaded data for easy testing has been loaded into the
JDBC and db folders respectively
No pre-requisites are required as long as all these files are included in the same folder
You may use and sqlite browser to view the data stored in the database and verify its correct functioning
I recommend the following browser because I used it while testing, it can be downloaded at the following link
https://sqlitebrowser.org/

Please use the following commands to compile and execute the program containing main method

For Non-Windows:

javac Test.java
java -classpath ".:JDBC/sqlite-jdbc-3.7.2.jar" Test

For Windows:

javac Test.java
java -classpath ".;JDBC/sqlite-jdbc-3.7.2.jar" Test