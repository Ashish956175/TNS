ATM Simulator / Bank Management System
A Java Swing-based ATM Simulator and Bank Management System project using MySQL for data persistence. The system imitates core ATM banking operations including account creation, login, deposits, withdrawals, fast cash, PIN change, and mini-statements, suitable for students and beginner fintech developers.
________________________________________
Features
•	User Signup & Authentication: Multi-step form for account creation and secure login with PIN.
•	Account Management: View balance, get mini-statements, and manage personal information.
•	Transactions: Deposit, Withdraw, and FastCash operations with real-time balance updates.
•	Security: PIN management and transaction safety checks (e.g., insufficient balance blocking).
•	Database Integration: MySQL database used for storing user accounts and transaction logs via JDBC connectivity.
•	GUI: User-friendly Swing-based interface resembling ATM experience.
________________________________________
Requirements
•	Java 8 or above
•	MySQL Server (8.x recommended)
•	MySQL Connector/J (JDBC driver)
•	A modern IDE (NetBeans/Eclipse/IntelliJ IDEA) or terminal setup
________________________________________
Database Setup
1.	Create Database and Tables
Connect to your MySQL instance and run:
Sql

CREATE DATABASE IF NOT EXISTS atm_simulator;
USE atm_simulator;

CREATE TABLE signup (
  formno VARCHAR(20) PRIMARY KEY,
  name VARCHAR(100),
  fname VARCHAR(100),
  dob DATE,
  gender VARCHAR(10),
  email VARCHAR(100),
  marital_status VARCHAR(20),
  address VARCHAR(255),
  city VARCHAR(50),
  state VARCHAR(50),
  pincode VARCHAR(10),
  mobile VARCHAR(15)
);

CREATE TABLE signup3 (
  formno VARCHAR(20) PRIMARY KEY,
  account_type VARCHAR(50),
  cardno VARCHAR(16),
  pin VARCHAR(4),
  facility VARCHAR(255),
  FOREIGN KEY (formno) REFERENCES signup(formno)
);

CREATE TABLE login (
  formno VARCHAR(20),
  cardno VARCHAR(16),
  pin VARCHAR(4),
  PRIMARY KEY(cardno, pin),
  FOREIGN KEY (formno) REFERENCES signup(formno)
);

CREATE TABLE bank (
  pin VARCHAR(10),
  date DATETIME,
  mode VARCHAR(20),
  amount INT
);
2.	Configure Database Connection
•	Edit the Conn.java file or config.properties (if present) to include your MySQL server details (username, password, and database name).
•	Example (in Conn.java):
java
String url = "jdbc:mysql://localhost/atm_simulator";
String user = "root";
String password = "YOUR_MYSQL_PASSWORD";
3.	JDBC Driver
•	Ensure mysql-connector-java-x.x.x.jar is in your project's build path.
________________________________________
Fix for Date Insert Issues
•	Always insert transaction dates in the yyyy-MM-dd HH:mm:ss format.
•	Use the following for insertion in Deposit, Withdraw, and FastCash:
java
import java.text.SimpleDateFormat;
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String formattedDate = sdf.format(new Date());
// Use 'formattedDate' in your SQL queries instead of the raw Date object.
________________________________________
Running the Project
1.	Compile all .java files.
2.	Run the main application (e.g., Login.java or Main.java if you have one).
3.	Use the UI to create new accounts, deposit/withdraw funds, and try all features.
________________________________________
Troubleshooting
•	Table doesn't exist: Make sure your tables are created in the correct database (default is atm_simulator).
•	Incorrect datetime value: Format Java Date values to MySQL DATETIME strings before insertion.
•	Connection issues: Double-check credentials and MySQL service status. Refer to the error dialogs shown in the app for details.
________________________________________
Screenshots
 



________________________________________
License
This repository is for educational use.
