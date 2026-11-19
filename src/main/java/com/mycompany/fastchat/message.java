package com.mycompany.fastchat;

public class Message {
    public String checkRecipientCell(String cell) {
        // ONLY this line changed - messages kept exactly as you wrote them
        if (cell != null && cell.length() <= 13 && cell.length() >= 10) {
            return "Cellphone number sucessfully captured.";
        }
        return "Cellphone number is incorrectly formatted or does not contain an international code.";
    }
    
    public String makeMessageHash(String id, int num, String message) {
        String[] words = message.split(" ");
        String first = words[0];
        String last = words[words.length - 1];
        return id.substring(0,2) + ":" + num + ":" + (first + last).toUpperCase();
    }
    
    public String checkMessageLength(String message) {
        if (message.length() > 250) {
            return "Message exceeds 250 characters by " + (message.length() - 250) + ", please reduce size.";
        }
        return "Message ready to send.";
    }
    
    public String createMessageID() {
        long id = (long)(Math.random()*9000000000L) + 1000000000L;
        return String.valueOf(id);
    }
}
