package com.mycompany.fastchat;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Fastchat {
    static ArrayList<String> storedMessages = new ArrayList<>();
    static ArrayList<String> storedIDs = new ArrayList<>();
    static ArrayList<String> storedHashes = new ArrayList<>();
    static ArrayList<String> storedRecipients = new ArrayList<>();

    public static void main(String[] args) {
        Login login = new Login();
        Message msg = new Message();

        String username = JOptionPane.showInputDialog("Enter username (must contain _ and <=5 chars, e.g. kyl_1):");
        String password = JOptionPane.showInputDialog("Enter password (8+ chars, capital, number, special, e.g. Ch&&sec@ke99!):");
        String cell = JOptionPane.showInputDialog("Enter cellphone (e.g. +27838968976):");

        String regResult = login.registerUser(username, password, cell);
        JOptionPane.showMessageDialog(null, regResult);
        if (!regResult.equals("User registered successfully.")) {
            return;
        }

        String enteredUser = JOptionPane.showInputDialog("Login - Enter username:");
        String enteredPass = JOptionPane.showInputDialog("Login - Enter password:");
        boolean loggedIn = login.loginUser(enteredUser, enteredPass, username, password);
        JOptionPane.showMessageDialog(null, login.returnLoginStatus(loggedIn));
        if (!loggedIn) {
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        int messageCount = 0;
        while (true) {
            String menu = JOptionPane.showInputDialog("Choose:\n1. Send Message\n2. Show Recently Sent Messages\n3. Quit\n4. Stored Messages Menu");
            if (menu == null || menu.equals("3")) {
                JOptionPane.showMessageDialog(null, "Total messages sent: " + messageCount);
                break;
            }
            if (menu.equals("1")) {
                String recipient = JOptionPane.showInputDialog("Enter recipient cell (e.g. +27718693002):");
                JOptionPane.showMessageDialog(null, msg.checkRecipientCell(recipient));
                String text = JOptionPane.showInputDialog("Enter message (max 250 chars):");
                JOptionPane.showMessageDialog(null, msg.checkMessageLength(text));
                if (text.length() > 250) {
                    continue;
                }
                String id = msg.createMessageID();
                String hash = msg.makeMessageHash(id, messageCount, text);
                String choice = JOptionPane.showInputDialog("1. Send\n2. Disregard\n3. Store to send later");
                if (choice.equals("1")) {
                    JOptionPane.showMessageDialog(null, "Message sent!\nID: " + id + "\nHash: " + hash);
                    messageCount++;
                } else if (choice.equals("3")) {
                    storedMessages.add(text);
                    storedIDs.add(id);
                    storedHashes.add(hash);
                    storedRecipients.add(recipient);
                    saveToJson();
                    JOptionPane.showMessageDialog(null, "Message successfully stored.");
                    messageCount++;
                }
            } else if (menu.equals("4")) {
                if (storedMessages.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No stored messages.");
                } else {
                    String out = "Stored Messages:\n";
                    for (int i = 0; i < storedMessages.size(); i++) {
                        out += "To: " + storedRecipients.get(i) + "\nID: " + storedIDs.get(i) + "\nHash: " + storedHashes.get(i) + "\nMsg: " + storedMessages.get(i) + "\n\n";
                    }
                    JOptionPane.showMessageDialog(null, out);
                }
            }
        }
    }

    public static void saveToJson() {
        try {
            FileWriter writer = new FileWriter("storedMessages.json");
            writer.write("[\n");
            for (int i = 0; i < storedMessages.size(); i++) {
                writer.write("{\"recipient\":\"" + storedRecipients.get(i) + "\",\"id\":\"" + storedIDs.get(i) + "\",\"hash\":\"" + storedHashes.get(i) + "\",\"message\":\"" + storedMessages.get(i) + "\"}");
                if (i < storedMessages.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving JSON: " + e.getMessage());
        }
    }
}
