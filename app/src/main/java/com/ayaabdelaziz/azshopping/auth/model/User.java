package com.ayaabdelaziz.azshopping.auth.model;

public class User {

    public String name;
    public String email;
    public String password;
    public String avatar;
    public int id;
    public String role;
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String avatar, int id, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.id = id;
        this.role = role;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
