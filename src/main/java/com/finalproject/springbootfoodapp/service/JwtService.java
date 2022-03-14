package com.finalproject.springbootfoodapp.service;

import com.finalproject.springbootfoodapp.entity.reqres.JwtResponse;
import com.finalproject.springbootfoodapp.entity.User;
import com.finalproject.springbootfoodapp.repository.UserRepository;
import com.finalproject.springbootfoodapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public JwtResponse createJwtToken(User user) {

        String newGeneratedToken = jwtUtil.generateToken(user.getEmail());

        return new JwtResponse(
                user,
                newGeneratedToken,
                new Date(jwtUtil.getExpirationDateFromToken(newGeneratedToken).getTime())
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> checkedUser = userRepository.findByEmail(email);
        User user;

        if (checkedUser.isPresent()) {
            user = checkedUser.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getAuthorities(user));
        } else {
            throw new UsernameNotFoundException("User with email: " + email + " doesn't exists.");
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(User user) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName()));

        return authorities;
    }

}
