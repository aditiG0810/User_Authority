🚀User Authority Project

This Java-based project provides a simple user authority system with registration and login functionality 🔒. It uses MySQL as the database to store user information 📈.

Features ✅
- User registration with auto-generated username and password
- User login with username and password validation
- Logout functionality
- Secure database connections

Requirements 📋
- Java
- MySQL
- JDBC (Java Database Connectivity)

Usage 📄
1. Run the program and select an option:
    - Register: Create a new user account.
    - Login: Log in to an existing user account.
    - Logout: Exit the program.
2. Follow the prompts to enter user information or login credentials.

Database Setup 📊
- To use this project, you'll need to create a MySQL database with a users table.
 CREATE DATABASE b193;
 CREATE TABLE users (
  user_id INT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  mobNo VARCHAR(20),
  dob DATE,
  username VARCHAR(255),
  password VARCHAR(255)
);





