package com.finalproject.springbootfoodapp.service;

import com.finalproject.springbootfoodapp.entity.reqres.JwtRequest;
import com.finalproject.springbootfoodapp.entity.reqres.JwtResponse;
import com.finalproject.springbootfoodapp.entity.User;
import com.finalproject.springbootfoodapp.exception.UserLoginException;
import com.finalproject.springbootfoodapp.exception.UserRegisterException;
import com.finalproject.springbootfoodapp.repository.RoleRepository;
import com.finalproject.springbootfoodapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    /** user registration */
    public String register(User user) {

        Optional<User> checkUserEmail = userRepository.findByEmail(user.getEmail());

        if (checkUserEmail.isEmpty()) {
            user.setRole(roleRepository.findByRoleName("STANDARD").get()); // BASIC USER
            user.setActive(true);
            user.setPassword(getEncodedPassword(user.getPassword()));
            this.userRepository.save(user);
            return "User with email " + user.getEmail() + " successfully registered!";
        } else {
            throw new UserRegisterException("Email already used!");
        }
    }

    /** user login */
    public JwtResponse login(JwtRequest jwtRequest) throws Exception {

        Optional<User> userToLogin = userRepository.findByEmail(jwtRequest.getEmail());

        if (userToLogin.isPresent()) {
            if (isPasswordMatch(jwtRequest.getPassword(), userToLogin.get().getPassword())) {
                if (userToLogin.get().isActive()) {
                    return jwtService.createJwtToken(jwtRequest, userToLogin.get());
                } else {
                    throw new UserLoginException("Account not active. Please contact administrator.");
                }
            } else {
                throw new UserLoginException("Incorrect password.");
            }
        } else {
            throw new UserLoginException("There is no account for the specified email.");
        }
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
