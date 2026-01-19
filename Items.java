package org.example.passwordmanager;

import javafx.beans.property.SimpleStringProperty;

public class Items {

    private final SimpleStringProperty website;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public Items(String website, String username, String password) {
        this.website = new SimpleStringProperty(website);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getWebsite() {
        return website.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty websiteProperty() {
        return website;
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }
}
