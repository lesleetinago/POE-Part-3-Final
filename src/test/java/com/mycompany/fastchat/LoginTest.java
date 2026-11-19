package com.mycompany.fastchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    Login login = new Login();

    @Test
    public void testCheckUserNameCorrect() {
        // POE: kyl_1 should pass
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrect() {
        // POE: kyle!!!!!!! should fail
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityCorrect() {
        // POE: Ch&&sec@ke99! should pass
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityIncorrect() {
        // POE: password should fail
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumberCorrect() {
        // POE: +27838968976 should pass
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberIncorrect() {
        // POE: 08966553 should fail
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegisterUserSuccess() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testRegisterUserBadUsername() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUserBadPassword() {
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.", result);
    }

    @Test
    public void testRegisterUserBadCell() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    @Test
    public void testLoginUserSuccess() {
        // YOUR method: loginUser(enteredUser, enteredPass, realUser, realPass) - 4 inputs
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUserFail() {
        assertFalse(login.loginUser("kyl_1", "wrongpass", "kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        // YOUR method: returnLoginStatus(boolean)
        assertEquals("Welcome, it is great to see you again.", login.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatusFail() {
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus(false));
    }
}
