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


Individual Constraints

Please try to implement all the individual constraints. Try as much as you can.
Individual Constraints:
If possible add GUI using a Swing or any other equivalent. Add an option to block the card such that no further transacions are possible. Give the user an option to choose time limit after which the card gets blocked (say 5 mins, 10 mins etc)

Denominations, processing fee


Note: You have to compulsory implement individual constraints and at least 3 out of 7 common constraints. If you have already implemented some of them, implement the remaining ones. There is a possibility that your individual constraints and common constraints might match. If that is the case then during grading we will consider them as common constraints. 


//note to self
//priority list
GUI - interface design
password encryption
mini statement
card blocking

changing pin and basic details
transfer account to account