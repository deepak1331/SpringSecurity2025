
package com.learn.SpringSecurity2025.service;

import com.learn.SpringSecurity2025.entity.User;
import com.learn.SpringSecurity2025.model.UserPrincipal;
import com.learn.SpringSecurity2025.repo.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            log.info("User not found with username: {}", username);
            return new UsernameNotFoundException("User not found with username: " + username);
        });
        return new UserPrincipal(user);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}

