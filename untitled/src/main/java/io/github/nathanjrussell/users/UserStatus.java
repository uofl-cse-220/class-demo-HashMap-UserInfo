package io.github.nathanjrussell.users;

public enum UserStatus {
    USER_NOT_FOUND(404, "User not found"),
    USER_INSERTED(201, "User successfully inserted"),
    USER_DELETED(200, "User successfully deleted"),
    USER_UPDATED(200, "User successfully updated");

    private final int statusCode;
    private final String message;

    // Constructor
    UserStatus(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Getter for status code
    public int getStatusCode() {
        return statusCode;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Override toString for better display
    @Override
    public String toString() {
        return statusCode + ": " + message;
    }
}
