package com.ayaabdelaziz.azshopping.auth.model;

public class LoginResponse {

    String email;
    String password;

    public LoginResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
