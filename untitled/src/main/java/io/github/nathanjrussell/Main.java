package io.github.nathanjrussell;

import io.github.nathanjrussell.users.UserDatabase;
import io.github.nathanjrussell.users.UserInfo;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the UserDatabase
        UserDatabase db = new UserDatabase();

        // Create users using the builder
        UserInfo user1 = new UserInfo.Builder()
                .userID(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .birthMonth(5)
                .birthDate(15)
                .birthYear(1990)
                .ssn4("6789")
                .addInitialNote("User created successfully.")
                .build();

        UserInfo user2 = new UserInfo.Builder()
                .userID(2)
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@example.com")
                .birthMonth(7)
                .birthDate(20)
                .birthYear(1985)
                .ssn4("4321")
                .addInitialNote("Account initialized.")
                .build();

        // Insert users into the database
        System.out.println("Insert User 1: " + db.insertUser(user1).getMessage());
        System.out.println("Insert User 2: " + db.insertUser(user2).getMessage());

        // Attempt to insert User 1 again (should update instead)
        System.out.println("Re-insert User 1: " + db.insertUser(user1).getMessage());

        // Retrieve and display user information
        System.out.println("\nRetrieving User 1:");
        UserInfo retrievedUser1 = db.getUser(1);
        System.out.println(retrievedUser1);

        // Add notes to a user
        System.out.println("\nAdding notes to User 1:");
        db.addNoteToUser(1, "User logged in on 11/20/2024.");
        db.addNoteToUser(1, "Updated email address.");
        System.out.println(db.getUserNotes(1));

        // Update specific fields for a user
        System.out.println("\nUpdating User 1's email:");
        db.updateUser(1, "email", "new.email@example.com");
        System.out.println(db.getUser(1));

        System.out.println("\nUpdating User 2's first name:");
        db.updateUser(2, "firstName", "Janet");
        System.out.println(db.getUser(2));

        // Delete a user
        System.out.println("\nDeleting User 1:");
        System.out.println("Delete Status: " + db.deleteUser(1).getMessage());

        // Attempt to retrieve a deleted user
        System.out.println("\nRetrieving deleted User 1:");
        UserInfo deletedUser = db.getUser(1);
        System.out.println(deletedUser == null ? "User not found." : deletedUser);

        // Attempt to delete the same user again
        System.out.println("\nAttempting to delete User 1 again:");
        System.out.println("Delete Status: " + db.deleteUser(1).getMessage());

        // Show all notes for an existing user
        System.out.println("\nNotes for User 2:");
        System.out.println(db.getUserNotes(2));
    }
}
