package com.learn.SpringSecurity2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String get(){
        return "<h1> Welcome Friend !!! </h1>";
    }
}
