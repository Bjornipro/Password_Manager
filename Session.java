package org.example.passwordmanager;

public class Session {
    private static User currentUser;
    private static Safe_items vault;

    // Set the logged-in user
    public static void setUser(User user) {
        currentUser = user;
        vault = new Safe_items();
    }

    // Get the logged-in user
    public static User getUser() {
        return currentUser;
    }

    // Get the vault for current user
    public static Safe_items getVault() {
        if (vault == null) {
            vault = new Safe_items();
        }
        return vault;
    }

    // Check if a user is logged in
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    // Add this method to fix the error
    public static void logout() {
        currentUser = null;
        vault = null;
    }

    // Clear session (logout) - alternative name
    public static void clear() {
        currentUser = null;
        vault = null;
    }
}