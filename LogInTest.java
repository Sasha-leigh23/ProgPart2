package com.mycompany.progpart2maven;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogInTest {

    @Test
    public void testUsernameSuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertTrue(user.checkUsername(), "Username containing an underscore and <= 5 characters should pass.");
    }

    @Test
    public void testUsernameFailure() {
        LogIn user = new LogIn("John", "Doe", "kyleeeeeee", "Password123!", "+27718693002");
        assertFalse(user.checkUsername(), "Username without an underscore or too long should fail.");
    }

    @Test
    public void testPasswordComplexitySuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertTrue(user.checkPasswordComplexity(), "Password meeting length, uppercase, number, and special character rules should pass.");
    }

    @Test
    public void testPasswordComplexityFailure() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "password", "+27718693002");
        assertFalse(user.checkPasswordComplexity(), "Password missing numbers, caps, or special characters should fail.");
    }

    @Test
    public void testCellNumberSuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertTrue(user.checkCellphoneNumber(), "Cell number starting with +27 followed by 9 digits should pass regex validation.");
    }

    @Test
    public void testCellNumberFailure() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "0857597588");
        assertFalse(user.checkCellphoneNumber(), "Cell number missing the +27 prefix or incorrect length should fail.");
    }

    @Test
    public void testLoginSuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertTrue(user.loginUser("kyl_1", "Password123!"), "Correct credentials should return true for login.");
    }

    @Test
    public void testLoginFailure() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        assertFalse(user.loginUser("kyl_1", "WrongPass"), "Incorrect credentials should return false for login.");
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        LogIn user = new LogIn("John", "Doe", "kyl_1", "Password123!", "+27718693002");
        String expected = "Welcome John, Doe it is great to see you again.";
        assertEquals(expected, user.returnLoginStatus(true), "Success login status message should match exact marker formatting.");
    }
}
