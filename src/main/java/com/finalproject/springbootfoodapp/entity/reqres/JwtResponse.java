package com.finalproject.springbootfoodapp.entity.reqres;

import com.finalproject.springbootfoodapp.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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

}
