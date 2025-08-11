package com.learn.SpringSecurity2025.service.impl;

import com.learn.SpringSecurity2025.entity.User;
import com.learn.SpringSecurity2025.repo.UserRepository;
import com.learn.SpringSecurity2025.service.JwtService;
import com.learn.SpringSecurity2025.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public User save(User user) {
        log.debug("User Registration : {}", user);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String verify(User user) {
        Authentication authenticator = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()));
        //return authenticator.isAuthenticated() ? "Authentication Success": "Authentication Failed";
       return authenticator.isAuthenticated() ? jwtService.generateToken(user.getUsername()) : "Authentication Failed";

    }
}
