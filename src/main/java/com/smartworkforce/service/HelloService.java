package com.smartworkforce.service;


import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getWelcomeMessage()
    {
        return "Hello  Welcome to Smart Workforce Management System!";
    }
}
