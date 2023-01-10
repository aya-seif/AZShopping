package com.ayaabdelaziz.azshopping.auth.remote;

public class UserResponse {

    public String name;
    public String email;
    public String password;
    public String avatar;
    public int id;
    public String role;

    public UserResponse(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserResponse(String name, String email, String password, String avatar, int id, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.id = id;
        this.role = role;
    }
}
