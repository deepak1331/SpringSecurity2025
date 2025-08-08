package com.learn.SpringSecurity2025.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String get(HttpServletRequest request) {
        return String.format("<h1> Welcome Friend !!! " +
                "</h1> <h5>SessionID: %s", request.getSession().getId());
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }


    /*
    @GetMapping("/admin/{username}")
    public String getAdmin(@PathVariable final String username) {
        return String.format("<h1> Welcome %s !!! </h1> <p> Role: ADMIN </p>", username.toUpperCase());
    }

    @GetMapping("/user/{username}")
    public String getUser(@PathVariable final String username) {
        return String.format("<h1> Welcome %s !!! </h1> <p> Role: USER </p>", username.toUpperCase());
    }*/

}