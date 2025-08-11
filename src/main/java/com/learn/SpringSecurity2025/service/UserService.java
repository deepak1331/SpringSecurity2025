package com.learn.SpringSecurity2025.service;

import com.learn.SpringSecurity2025.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public User save(User user);

    String verify(User user);
}
