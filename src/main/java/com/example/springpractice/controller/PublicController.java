package com.example.springpractice.controller;

import com.example.springpractice.entity.User;
import com.example.springpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class PublicController
{   @Autowired
    private UserService userService;
    @GetMapping("/health-check")
    public String healthCheck()
    {
        return "Successfull";
    }

    @PostMapping("/createuser")
    public void createUser(@RequestBody User user)
    {
        userService.saveEntry(user);
    }

}
