package com.mycompany.fastchat;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Fastchat {
    // Final POE - Arrays for Part 3
    static ArrayList<String> sentMessages = new ArrayList<>();
    static ArrayList<String> storedMessages = new ArrayList<>();
    static ArrayList<String> disregardedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();
    static ArrayList<String> recipients = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();
        Message msgHelper = new Message();
        
        System.out.println("REGISTER");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter cell (+27...): ");
        String cell = sc.nextLine();
        
        String regResult = login.registerUser(username, password, cell);
        System.out.println(regResult);
        if (!regResult.equals("User registered successfully.")) return;
        
        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: ");
        String lu = sc.nextLine();
        System.out.print("Enter password: ");
        String lp = sc.nextLine();
        
        boolean logged = login.loginUser(lu, lp, username, password);
        System.out.println(login.returnLoginStatus(logged));
        if (!logged) return;
        
        System.out.println("\nWelcome to FastChat!");
        int messageNumber = 0;

        while(true){
            System.out.println("1) Send Message 2) Show Report 3) Quit 4) Stored Messages");
            
            System.out.print("Choice: ");
            String choice = sc.nextLine();
            if(choice.equals("1")){
                System.out.print("How many messages do you want to send? ");
                int count = Integer.parseInt(sc.nextLine());
                for(int i = 0; i < count; i++){
                    messageNumber++;
                    System.out.print("Recipient (+...): ");
                    String rec = sc.nextLine();
                    System.out.println(msgHelper.checkRecipientCell(rec));
                    System.out.print("Enter message (max 250): ");
                    String message = sc.nextLine();
                    System.out.println(msgHelper.checkMessageLength(message));
                    if(message.length() > 250){
                        messageNumber--;
                        continue;
