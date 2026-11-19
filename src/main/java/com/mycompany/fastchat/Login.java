    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fastchat;
public class Login {
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    //check password//
    
    public boolean checkPasswordComplexity(String password) {
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-={}:;\"'<>,.?/].*");
        boolean longEnough = password.length() >= 8;
        return hasUpper && hasDigit && hasSpecial && longEnough;
    }
    // check if cellphone has right country code//
    
    public boolean checkCellPhoneNumber(String cell) {
        return cell.matches("^\\+27\\d{9}$");
    }
    // Regististration//
    
    public String registerUser(String username, String password, String cell) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "User registered successfully.";
    }
    
    public boolean loginUser(String enteredUser, String enteredPass, String realUser, String realPass) {
        return enteredUser.equals(realUser) && enteredPass.equals(realPass);
    }
    //final screen//
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome, it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
