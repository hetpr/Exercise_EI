package assignment.chat.model;

import java.util.Objects;

public class User {
    private final String username;

    public User(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("username cannot be empty");
        }
        this.username = username.trim();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return username;
    }
}