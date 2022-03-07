package com.finalproject.springbootfoodapp.entity.reqres;

public class JwtRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public JwtRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JwtRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
