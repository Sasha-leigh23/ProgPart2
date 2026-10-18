
package com.mycompany.progpart2maven;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgPart2Maven {

    private static final List<Messages> SENT_MESSAGES = new ArrayList<>();
    private static int sentCount = 0;
    private static int processedCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. First Name and Last Name
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine().trim();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine().trim();

        System.out.println("Welcome " + firstName + " " + lastName
                + ", it's good to see you again!");

        // 2. Username Input (Up to 2 attempts)
        String username = "";

        for (int attempt = 1; attempt <= 2; attempt++) {
            System.out.print(attempt == 1
                    ? "Enter your username: "
                    : "Enter your username again: ");

            username = input.nextLine().trim();

            LogIn tempCheck = new LogIn(
                    firstName, lastName, username, "", "+27000000000");

            if (tempCheck.checkUsername()) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; "
                        + "please ensure that your username contains an "
                        + "underscore and is no more than 5 characters long.");
            }
        }

        // 3. Password Input (Up to 2 attempts)
        String password = "";

        for (int attempt = 1; attempt <= 2; attempt++) {
            System.out.print(attempt == 1
                    ? "Enter your password: "
                    : "Enter your password again: ");

            password = input.nextLine().trim();

            LogIn tempCheck = new LogIn(
                    firstName, lastName, username, password, "+27000000000");

            if (tempCheck.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; "
                        + "please ensure that it contains at least 8 "
                        + "characters, a capital letter, a number, "
                        + "and a special character.");
            }
        }

        // 4. Cell Number Input (Up to 2 attempts)
        String cellnumber = "";

        for (int attempt = 1; attempt <= 2; attempt++) {
            System.out.print(attempt == 1
                    ? "Enter your cell number: "
                    : "Enter your cell number again: ");

            cellnumber = input.nextLine().trim();

            LogIn tempCheck = new LogIn(
                    firstName, lastName, username, password, cellnumber);

            if (tempCheck.checkCellphoneNumber()) {
                System.out.println("Cell number successfully captured.");
                break;
            } else {
                System.out.println("Cell number is incorrectly formatted "
                        + "or does not contain an international code. "
                        + "Please correct the number and try again.");
            }
        }

        // Create the user account
        LogIn user = new LogIn(
                firstName, lastName, username, password, cellnumber);

        // 5. Login
        System.out.println("\nLOGIN");

        System.out.print("Enter username to login: ");
        String loginUser = input.nextLine().trim();

        System.out.print("Enter password to login: ");
        String loginPass = input.nextLine().trim();

        boolean success = user.loginUser(loginUser, loginPass);

        System.out.println(user.returnLoginStatus(success));

        // 6. QuickChat Menu
        if (success) {
            System.out.println("\nWelcome to QuickChat.");

            System.out.print("How many messages would you like to enter? ");
            int messageLimit = readPositiveInteger(input);

            boolean running = true;

            while (running) {
                System.out.println("\nQUICKCHAT MENU");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option (1-3): ");

                String option = input.nextLine().trim();

                switch (option) {
                    case "1":
                        sendMessages(input, messageLimit);
                        break;

                    case "2":
                        if (SENT_MESSAGES.isEmpty()) {
                            System.out.println("No messages have been sent.");
                        } else {
                            System.out.println("Recently sent messages:");

                            for (Messages message : SENT_MESSAGES) {
                                System.out.println(message.printMessages());
                            }
                        }
                        break;

                    case "3":
                        running = false;
                        System.out.println("Exiting QuickChat.");
                        break;

                    default:
                        System.out.println(
                                "Invalid option. Please enter 1, 2, or 3.");
                }
            }

            System.out.println(
                    "Total messages successfully sent: " + sentCount);
            System.out.println(
                    "Total messages processed: " + processedCount);
        }

        input.close();
    }

    // Read a positive whole number
    private static int readPositiveInteger(Scanner input) {
        while (true) {
            String value = input.nextLine().trim();

            try {
                int number = Integer.parseInt(value);

                if (number > 0) {
                    return number;
                }

                System.out.print(
                        "Enter a number greater than zero: ");

            } catch (NumberFormatException ex) {
                System.out.print(
                        "That is not a whole number. Try again: ");
            }
        }
    }

    // Send, disregard, or store messages
    private static void sendMessages(Scanner input, int messageLimit) {
        if (processedCount >= messageLimit) {
            System.out.println(
                    "You have reached the message limit you selected.");
            return;
        }

        while (processedCount < messageLimit) {
            int messageNumber = processedCount + 1;

            System.out.println("\n--- Message " + messageNumber
                    + " of " + messageLimit + " ---");

            // Validate recipient
            System.out.print(
                    "Enter recipient in international format "
                    + "(example +27718693002): ");

            String recipient = input.nextLine().trim();

            Messages draftForValidation = new Messages(
                    messageNumber, recipient, "placeholder");

            String recipientStatus =
                    draftForValidation.checkRecipientCell();

            System.out.println(recipientStatus);

            if (!recipientStatus.startsWith(
                    "Cell phone number successfully")) {
                System.out.println("Please enter a valid recipient.");
                continue;
            }

            // Read message text
            System.out.print(
                    "Enter your message (maximum 250 characters): ");

            String text = input.nextLine();

            Messages message = new Messages(
                    messageNumber, recipient, text);

            String lengthStatus = message.checkMessageLength();

            System.out.println(lengthStatus);

            if (!lengthStatus.equals("Message ready to send.")) {
                System.out.println(
                        "Please enter a message within the allowed length.");
                continue;
            }

            // Choose message action
            System.out.println("Choose what to do with this message:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            System.out.print("Your choice: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    SENT_MESSAGES.add(message);
                    sentCount++;
                    processedCount++;

                    System.out.println("Message successfully sent.");
                    System.out.println(message.printMessages());
                    break;

                case "2":
                    System.out.print(
                            "Enter 0 to confirm disregarding the message: ");

                    String confirm = input.nextLine().trim();

                    if (confirm.equals("0")) {
                        processedCount++;
                        System.out.println("Message disregarded.");
                    } else {
                        System.out.println(
                                "Disregard cancelled. Message not processed.");
                    }
                    break;

                case "3":
                    try {
                        storeMessage(message);
                        processedCount++;

                        System.out.println("Message successfully stored.");
                        System.out.println(message.printMessages());

                    } catch (IOException ex) {
                        System.out.println(
                                "The message could not be stored: "
                                + ex.getMessage());
                    }
                    break;

                default:
                    System.out.println(
                            "Invalid message option. Please try again.");
                    break;
            }

            // Return to menu after processing one message
            return;
        }

        System.out.println(
                "You have reached the message limit you selected.");
    }

    // Store messages in a JSON file
    private static void storeMessage(Messages message) throws IOException {
        Path file = Path.of("storedMessages.json");

        String existing = "[]";

        if (Files.exists(file)) {
            existing = Files.readString(
                    file, StandardCharsets.UTF_8).trim();
        }

        String object = message.toJson();
        String updated;

        if (existing.isEmpty() || existing.equals("[]")) {
            updated = "[" + object + "]";
        } else if (existing.endsWith("]")) {
            updated = existing.substring(0, existing.length() - 1)
                    + "," + object + "]";
        } else {
            throw new IOException(
                    "storedMessages.json is not a valid JSON array.");
        }

        Files.writeString(
                file,
                updated,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}