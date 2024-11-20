package io.github.nathanjrussell.users;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private final Map<Integer, UserInfo> users = new HashMap<>();

    // Method to insert a user
    public UserStatus insertUser(UserInfo user) {
        if (users.containsKey(user.userID())) {
            return UserStatus.USER_UPDATED;
        }
        users.put(user.userID(), user);
        return UserStatus.USER_INSERTED;
    }

    // Method to delete a user
    public UserStatus deleteUser(Integer userID) {
        if (users.remove(userID) != null) {
            return UserStatus.USER_DELETED;
        }
        return UserStatus.USER_NOT_FOUND;
    }

    // Method to retrieve a user
    public UserInfo getUser(Integer userID) {
        return users.get(userID);
    }

    // Method to update a user field
    public UserStatus updateUser(Integer userID, String fieldName, Object newValue) {
        UserInfo existingUser = users.get(userID);
        if (existingUser == null) {
            return UserStatus.USER_NOT_FOUND;
        }

        UserInfo.Builder builder = new UserInfo.Builder()
                .userID(existingUser.userID())
                .firstName(existingUser.firstName())
                .lastName(existingUser.lastName())
                .email(existingUser.email())
                .birthMonth(existingUser.birthMonth())
                .birthDate(existingUser.birthDate())
                .birthYear(existingUser.birthYear())
                .ssn4(existingUser.ssn4());

        existingUser.notes().forEach(builder::addInitialNote);

        switch (fieldName) {
            case "firstName" -> builder.firstName((String) newValue);
            case "lastName" -> builder.lastName((String) newValue);
            case "email" -> builder.email((String) newValue);
            case "birthMonth" -> builder.birthMonth((int) newValue);
            case "birthDate" -> builder.birthDate((int) newValue);
            case "birthYear" -> builder.birthYear((int) newValue);
            case "ssn" -> builder.ssn4((String) newValue);
            default -> {
                return UserStatus.USER_NOT_FOUND; // Invalid field
            }
        }

        UserInfo updatedUser = builder.build();
        users.put(userID, updatedUser);
        return UserStatus.USER_UPDATED;
    }

    // Method to add a note to a user
    public UserStatus addNoteToUser(Integer userID, String note) {
        UserInfo user = users.get(userID);
        if (user == null) {
            return UserStatus.USER_NOT_FOUND;
        }
        user.addNote(note);
        return UserStatus.USER_UPDATED;
    }

    // Method to get all notes for a user
    public String getUserNotes(Integer userID) {
        UserInfo user = users.get(userID);
        if (user == null) {
            return null;
        }
        return user.getAllNotes();
    }
}
