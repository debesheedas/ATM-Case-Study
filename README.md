# ATM-Case-Study
PM Case Study 2 ATM

Please compile all the java files once before trying to run Test.java which contains the main method
The JDBC driver and the existing database with some preloaded data for easy testing has been loaded into the
JDBC and db folders respectively
No pre-requisites are required as long as all these files are included in the same folder
You may use and sqlite browser to view the data stored in the database and verify its correct functioning
I recommend the following browser because I used it while testing, it can be downloaded at the following link
https://sqlitebrowser.org/
IMPORTANT: Some jar files which are necessary for the successful running of the code have also been included in the repository. Please make sure they are included in your path. I have used a settings.json(also included in the repository) to ensure that because I use vscode, however it(absolute path) might be different for you, so please check that before running the code. 

Please use the following commands to compile and execute the program containing main method

For Non-Windows:

javac Test.java

java -classpath ".:JDBC/sqlite-jdbc-3.7.2.jar" Test

For Windows:

javac Test.java

java -classpath ".;JDBC/sqlite-jdbc-3.7.2.jar" Test




The Individual constraints given to me are as follows:

Please try to implement all the individual constraints. Try as much as you can.
Individual Constraints:
If possible add GUI using a Swing or any other equivalent. Add an option to block the card such that no further transacions are possible. Give the user an option to choose time limit after which the card gets blocked (say 5 mins, 10 mins etc)

Note: You have to compulsory implement individual constraints and at least 3 out of 7 common constraints. If you have already implemented some of them, implement the remaining ones. There is a possibility that your individual constraints and common constraints might match. If that is the case then during grading we will consider them as common constraints. 

I have implemented all my individual constraints.
1. I have implemented the GUI using Java Swing classes. I have made 2 classes, AuthenticateGUI.java and MainMenuGUI.java. The program starts with AuthenticateGUI.java which deals with the complete authenticaiton process. After a successful login, it takes the user to MainMenuGUI.java where all options of the menu are present. On clicking Exit button, the user will be logged out and the home page of the ATM, which is the AuthenticateGUI.java page, will be restarted and the next user can continue using without any interruption or inconvenience. Please click the Close window button at any time to exit the program. Care has been taken to save all appropriate data even if the window is closed unintentionally. An extra check through a dialogue box is also provided.

2. I have implemented the card blocking mechanism which blocks the card if for a given account number incorrect pin is entered more then 3 times. The card is unblocked after a few minutes, and this value is different for different accounts. The data for this is maintained in the database. You can change these valuse by changing the block time in change details. Hence user can choose this time. This data is maintained in BlockTime column in the database along with the calculated end of blocking time, stored in column name EndTime. Card is automatically unblocked after this end time is passed. 

Common Constraints
1. Password Encryption: I have used the Bcrypt algorithm for encryption and storing in database. I have not implemented the algorithm from scratch, Instead I have imported it from gitHub. All the information on the original writer for that code along with license details are mentioned in Bcrypt.java file. Hence anyone with access to database cannot see password of any account holder.

2. OTP mechanism. After Account number and PIN are verified to be correct, user must click send otp.
A random OTP is generated and thrown on the console, in this case terminal, to stimulate sending message to
corresponding phone number. On entering th correct OTP in the GUI, the login is successful.

5. I have added the option of changing details such as PIN, phone number and block time for the user. It can be found by clicking on Change Details button in Main Menu.
6. I have stored all the informaiton in SQLite Database. I have made 3 tables, 1 to store Bank details such as name of bank, code of bank which is same as first 4 characters of IFSC code to identify which account belongs to which bank form IFSC code exactly how it is in real life.
There is one table to store customers of the bank, it stores, column wise, Name of Account Holder, Account Number, PIN(in encrypted form), Current Balance in Account, Login Status, IFSC Code, Phone Number, Block Time, Block Status, End of Block Time.
Transactions Table stores all the Transactions conducted at that particular ATM, it has columns Unique reference ID of each transaction, Account Number,Type of Transaction, Amount of Transaction, Final Balance. 
7. The mini Statement is generated using the datat stored in transactions table. Based on account number only the corresponding transactions are retireived and printed on the terminal to simulate printing of mini statement on paper like in real life.  


Further functionalities that I have implemented
1. I have implemented the concept of having 2 different banks and ATMs - ICICI and SBI.
Records are maintained in 2 separate databases with appropriate names. Customer of any bank can use any ATM.
IF The Bank to which the ATM belongs to is the same bank as the Account of the customer, which is checked by checking the first 4 characters of the IFSC code, then no processing fee is charged. Otherwise a processing fee of Rupees 20 is charged.
Here, for the use case, on running the Test.java, the ATM belongs to State Bank of India. the variable atm1 refers to the bank to which the atm belongs and atm2 belongs to the other bank i.e. ICICI in this case, accoring to the default data filled in the database. 
2. I had in mind implementing the option of transferring funds from one account to another, even the option of transferring from one bank to another, that is why I structured it like this, unfortunately, I did not have enough time to do implement this part. 
3. All checks for whether balance is enough or not, including processing fee if applicable, checks if ATM cash balance is sufficient have been implemented. Processing fee is deducted from user account but not from ATM cash balance (exactly how it is in real life). ATM cash balance can be changed in the hard code or the database. Since user cannot modify that, that has not been included in the GUI.
4. I have not created the option of Admin in the GUI of ATM because the Admin here is basically the bank and the bank admin does not change settings by interacting with this user interface belonging to the ATM. 
5. I have used the object oriented concept of interface for user interface, which in the previous week I had implemented using command line and menu driven code, but in theis week I have implemented the same user interface with GUI classes. hence I have used object oriented concepts to extend on my existing code conveniently. In the GUIs I have used existing interfaces such as JFrame and ActionListener. I have used Encapsulation by encapsulating together data and methods of each entity in their respective classes, with access modifiers such as private wherever possible.


NOTE:
Many print statements such as (Updating Database, Checking Block Status, etc) that are useful while testing are placed strategically, I have tried to comment most of them out because they are not relevant to the user. However, Please free to uncomment any print statements and see what they are printing to get a sense of the actual working. 
