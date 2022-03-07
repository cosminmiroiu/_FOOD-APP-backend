package com.finalproject.springbootfoodapp.controller;

import com.finalproject.springbootfoodapp.entity.User;
import com.finalproject.springbootfoodapp.entity.reqres.JwtRequest;
import com.finalproject.springbootfoodapp.entity.reqres.JwtResponse;
import com.finalproject.springbootfoodapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody User user){
        return new ResponseEntity<>(this.userService.register(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) throws Exception {
        return new ResponseEntity<>(userService.login(jwtRequest), HttpStatus.OK);
    }

}
