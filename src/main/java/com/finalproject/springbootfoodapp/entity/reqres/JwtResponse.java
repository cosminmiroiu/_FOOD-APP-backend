package com.finalproject.springbootfoodapp.entity.reqres;

import com.finalproject.springbootfoodapp.entity.User;

import java.util.Date;

public class JwtResponse {

    private User user;
    private String jwtToken;
    private Date expirationDate;

    public JwtResponse(User user, String jwtToken, Date expirationDate) {
        this.user = user;
        this.user.setPassword("HIDDEN_BY_SECURITY");
        this.jwtToken = jwtToken;
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public JwtResponse setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public User getUser() {
        return user;
    }

    public JwtResponse setUser(User user) {
        this.user = user;
        return this;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public JwtResponse setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this;
    }
}
