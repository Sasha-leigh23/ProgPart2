//References 
//OpenAI. (2026). ChatGPT (June 27 Version) [Large Language Model]. https://chatgpt.com/
package com.mycompany.progpart2maven;

public class LogIn {

    // Assigning constructor parameters to class variables
    public LogIn(String firstName1, String lastName1, String username1, String password1, String number1) {
        this.firstName = firstName1;
        this.lastName = lastName1;
        this.username = username1;
        this.password = password1;
        this.cellnumber = number1;
    }
    
    //Declare the variables being used in the program
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String cellnumber;

    
    // Checks password complexity using basic character range comparisons
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            // Checks if the password contains a Capital letter (From 'A' to 'Z')
            if (ch >= 'A' && ch <= 'Z') {
                hasCapital = true;
            } 
            // Digit check (From '0' to '9')
            else if (ch >= '0' && ch <= '9') {
                hasNumber = true;
            } 
            // Special character check (Outside standard letters and numbers)
            else if (!(ch >= 'a' && ch <= 'z')) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // Checks cellphone number for the international code "+27" and digit checks using a regular expression
    public boolean checkCellphoneNumber() {
        if (cellnumber == null) {
            return false;
        }

        return cellnumber.matches("\\+27\\d{9}");
    }

    // Method checks the registration details
    public String registerUser() {
        if (!checkUsername()) {
            return "Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains "
                    + "at least 8 characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellphoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code; "
                    + "please correct the number and try again.";
        }
        return "Registration successful. Welcome!";
    }

    // generated with assistance from ChatGPT on 2026-09-09
    // Method checks login credentials
    public boolean loginUser(String usernameAttempt, String passwordAttempt) {
        return usernameAttempt.equals(username) && passwordAttempt.equals(password);
    }

    // Method returns the log In status of the user 
    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again";
        }
    }

    public boolean checkUsername() {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    // Method returns the username message
    public String returnUsernameMessage() {
        if (checkUsername()) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than 5 characters in length.";
        }
    }

    // Method returns the password message
    public String returnPasswordMessage() {
        if (checkPasswordComplexity()) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains "
                    + "at least 8 characters, a capital letter, a number, and a special character.";
        }
    }

    // Method returns the cell number message
    public String returnCellNumberMessage() {
        if (checkCellphoneNumber()) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code; "
                    + "please correct the number and try again.";
        }
    }
}
