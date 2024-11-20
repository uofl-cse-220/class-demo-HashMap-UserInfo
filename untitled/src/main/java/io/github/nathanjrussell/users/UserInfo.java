package io.github.nathanjrussell.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record UserInfo(
        Integer userID,
        String firstName,
        String lastName,
        String email,
        int birthMonth,
        int birthDate,
        int birthYear,
        String ssn4,
        List<String> notes
) {
    // Constructor to ensure notes is initialized
    public UserInfo {
        if (notes == null) {
            notes = new ArrayList<>();
        }
    }

    // Method to add a note
    public void addNote(String note) {
        notes.add(note);
    }

    // Method to retrieve all notes as a formatted string
    public String getAllNotes() {
        return String.join(System.lineSeparator(), notes);
    }

    // Nested static Builder subclass
    public static class Builder {
        private Integer userID;
        private String firstName;
        private String lastName;
        private String email;
        private int birthMonth;
        private int birthDate;
        private int birthYear;
        private String ssn4;
        private List<String> notes = new ArrayList<>();

        public Builder userID(Integer userID) {
            this.userID = userID;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder birthMonth(int birthMonth) {
            this.birthMonth = birthMonth;
            return this;
        }

        public Builder birthDate(int birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder birthYear(int birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public Builder ssn4(String ssn4) {
            this.ssn4 = ssn4;
            return this;
        }

        public Builder addInitialNote(String note) {
            this.notes.add(note);
            return this;
        }

        public UserInfo build() {
            // Validation for required fields and logical constraints
            Objects.requireNonNull(userID, "userID cannot be null");
            Objects.requireNonNull(firstName, "firstName cannot be null");
            Objects.requireNonNull(lastName, "lastName cannot be null");
            Objects.requireNonNull(email, "email cannot be null");
            Objects.requireNonNull(ssn4, "ssn cannot be null");

            if (birthMonth < 1 || birthMonth > 12) {
                throw new IllegalArgumentException("Invalid birthMonth: " + birthMonth);
            }
            if (birthDate < 1 || birthDate > 31) { // Simplified validation
                throw new IllegalArgumentException("Invalid birthDate: " + birthDate);
            }

            return new UserInfo(userID, firstName, lastName, email, birthMonth, birthDate, birthYear, ssn4, notes);
        }
    }
}
