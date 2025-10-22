package com.tap.test;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;
import java.util.Scanner;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Create an instance of UserDaoImpl
        UserDaoImpl userDao = new UserDaoImpl();

        // Step 1: Add a new user
        System.out.println("Adding a new user...");

        System.out.print("Enter user name: ");
        String username = scanner.nextLine();  // Read username

        System.out.print("Enter password: ");
        String password = scanner.nextLine();  // Read password

        System.out.print("Enter email: ");
        String email = scanner.nextLine();  // Read email

        System.out.print("Enter phone number: ");
        long phone = scanner.nextLong();  // Read phone number

        scanner.nextLine();  // Consume the newline character left by nextLong()

        System.out.print("Enter address: ");
        String address = scanner.nextLine();  // Read address

        System.out.print("Enter role: ");
        String role = scanner.nextLine();  // Read role

        // Get the current date for createdLoginDate and lastLoginDate
        Date currentDate = new Date();

        // Create a new user object
        User newUser = new User(0, username, password, email, phone, address, role, currentDate, currentDate);
        userDao.addUser(newUser);
        System.out.println("User added successfully!");

        // Step 2: Fetch the user by ID
        System.out.print("Enter the user ID to fetch the user: ");
        int userIdToFetch = scanner.nextInt();  // Read user ID to fetch

        // Fetch user by ID
        User fetchedUser = userDao.getUserById(userIdToFetch);
        if (fetchedUser != null) {
            System.out.println("Fetched User: ");
            System.out.println("ID: " + fetchedUser.getUserId());
            System.out.println("Username: " + fetchedUser.getUsername());
            System.out.println("Email: " + fetchedUser.getEmail());
            System.out.println("Role: " + fetchedUser.getRole());
        } else {
            System.out.println("User not found!");
        }

        // Step 3: Update the user details
        System.out.println("Updating user details...");

        if (fetchedUser != null) {
            System.out.print("Enter new username: ");
            String updatedUsername = scanner.next();  // Read new username

            System.out.print("Enter new email: ");
            String updatedEmail = scanner.next();  // Read new email

            System.out.print("Enter new phone: ");
            long updatedPhone = scanner.nextLong();  // Read new phone number

            scanner.nextLine();  // Consume the newline character

            System.out.print("Enter new address: ");
            String updatedAddress = scanner.nextLine();  // Read new address

            System.out.print("Enter new role: ");
            String updatedRole = scanner.nextLine();  // Read new role

            // Update the user details
            fetchedUser.setUsername(updatedUsername);
            fetchedUser.setEmail(updatedEmail);
            fetchedUser.setPhone(updatedPhone);
            fetchedUser.setAddress(updatedAddress);
            fetchedUser.setRole(updatedRole);
            fetchedUser.setLastLoginDate(new Date());  // Set the new last login date

            // Call the update method
            userDao.updateUser(fetchedUser);
            System.out.println("User updated successfully!");
        }

        // Step 4: Fetch the updated user
        System.out.println("Fetching the updated user...");
        User updatedUser = userDao.getUserById(fetchedUser.getUserId());
        if (updatedUser != null) {
            System.out.println("Updated User Details: ");
            System.out.println("ID: " + updatedUser.getUserId());
            System.out.println("Username: " + updatedUser.getUsername());
            System.out.println("Email: " + updatedUser.getEmail());
            System.out.println("Role: " + updatedUser.getRole());
        } else {
            System.out.println("User not found!");
        }
        
    }
}
