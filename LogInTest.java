package com.mycompany.progpart2maven;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogInTest {

    // Method tests if a valid username with an underscore and 5 or fewer characters passes validation
    @Test
    public void testUsernameSuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertTrue(user.checkUsername(), "Username containing an underscore and <= 5 characters should pass.");
    }

    // Method tests if an invalid username missing an underscore or exceeding length limits fails validation
    @Test
    public void testUsernameFailure() {
        LogIn user = new LogIn("John", "Doe", "kyleeeeeee", "Password123!", "+27718693002");
        assertFalse(user.checkUsername(), "Username without an underscore or too long should fail.");
    }

    // Method tests if a password meeting length, uppercase, number, and special character rules passes complexity checks
    @Test
    public void testPasswordComplexitySuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertTrue(user.checkPasswordComplexity(), "Password meeting length, uppercase, number, and special character rules should pass.");
    }

    // Method tests if a password missing required numbers, uppercase letters, or special characters fails complexity checks
    @Test
    public void testPasswordComplexityFailure() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "password", "+27718693002");
        assertFalse(user.checkPasswordComplexity(), "Password missing numbers, caps, or special characters should fail.");
    }

    // Method tests if a cellphone number starting with the +27 international prefix followed by 9 digits passes validation
    @Test
    public void testCellNumberSuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertTrue(user.checkCellphoneNumber(), "Cell number starting with +27 followed by 9 digits should pass regex validation.");
    }

    // Method tests if a cellphone number missing the +27 prefix or in an invalid format fails validation
    @Test
    public void testCellNumberFailure() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "0857597588");
        assertFalse(user.checkCellphoneNumber(), "Cell number missing the +27 prefix or incorrect length should fail.");
    }

    // Method tests if entering correct user credentials successfully logs in the user
    @Test
    public void testLoginSuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertTrue(user.loginUser("kyl_1", "Password123!"), "Correct credentials should return true for login.");
    }

    // Method tests if entering incorrect user credentials rejects the login attempt
    @Test
    public void testLoginFailure() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertFalse(user.loginUser("kyl_1", "WrongPass"), "Incorrect credentials should return false for login.");
    }

    // Method tests if the tester login status message returns properly formatted greeting
    @Test
    public void testReturnLoginStatusSuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        String expected = "Welcome John, Doe it is great to see you again.";
        assertEquals(expected, user.returnLoginStatus(true), "Success login status message should match exact marker formatting.");
    }
}