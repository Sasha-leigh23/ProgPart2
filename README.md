 QuickChat & Secure Authentication Console Application (Part 1 & Part 2)

A multi-class Java console application built using Apache Maven. This project integrates secure user registration, complex input validation, user authentication, and advanced message management (with JSON file persistence and JUnit 5 unit testing).



Project Overview

The application is structured into two primary modules:
1. Part 1: Secure Authentication System (LogIn.java)
   - Captures and validates user registration details (First name, Last name, Username, Password, and South African cell phone number).
   - Enforces strict security constraints: usernames must contain an underscore  and be 5 characters or less; passwords require 8+ characters, a capital letter, a number, and a special character; cell phone numbers must follow the international South African format.
   - Implements a secure login gate that authenticates user credentials before granting access to the messaging system.

2. Part 2: QuickChat Messaging System (Messages.java & ProgPartOneAssignment.java)
   - Provides an interactive console menu accessible only after successful login.
   - Allows users to enter, validate, and process a specified number of messages.
   - Implements unique 10-digit ID generation and automated message hashing.
   - Supports three distinct actions per message:
     -Send Message: Validates length  and displays message summary details.
     - Disregard Message: Prompts for confirmation to cancel or delete.
     - Store Message: Persists message data securely into a local `storedMessages.json` file.

Project Structure

pom.xml - Apache Maven project configuration and dependency management file.

storedMessages.json - Generated local storage file containing saved messages.

Source Directory

ProgPart2Maven.java - Main console application handling user input, registration flow, login gate, and the QuickChat interactive menu.

LogIn.java - User authentication class managing registration data, input validation logic (username, password, and cell regex), and status messaging.

Messages.java - Message management class handling unique ID generation, character length validation, message hashing, and JSON serialization.

Source Test Directory 

LogInTest.java — JUnit 5 unit test suite for verifying registration rules, password complexity, and login states.

MessagesTest.java — JUnit 5 unit test suite for verifying message length boundaries, recipient phone formats, and hash creation.
## 📁 Project Structure

