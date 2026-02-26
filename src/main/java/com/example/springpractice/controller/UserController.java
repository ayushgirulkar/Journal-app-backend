package com.example.springpractice.controller;


import com.example.springpractice.entity.User;
import com.example.springpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getall()
    {
        return userService.getAll();
    }

    @PostMapping
    public boolean createUser(@RequestBody User user)
    {
        userService.saveEntry(user);
        return true;
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUSer(@RequestBody User user,@PathVariable String userName)
    {
        User userDB= userService.findByUserName(user.getUserName());
        if(userDB!=null)
        {
            userDB.setUserName(user.getUserName());
            userDB.setPassword(user.getPassword());
            userService.saveEntry(userDB);
        }
        return  new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
