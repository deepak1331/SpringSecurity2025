package com.learn.SpringSecurity2025.service.impl;

import com.learn.SpringSecurity2025.entity.User;
import com.learn.SpringSecurity2025.repo.UserRepository;
import com.learn.SpringSecurity2025.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        log.info("User Registration : {}", user);
        return userRepository.save(user);
    }
}
