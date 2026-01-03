package com.example.springpractice.controller;

import com.example.springpractice.entity.JournalEntry;
import com.example.springpractice.entity.User;
import com.example.springpractice.service.JournalEntryService;
import com.example.springpractice.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController
{   @Autowired
    private UserService userService;

    @GetMapping("/alluser")
    public List<User>getAllUSers()
    {
        return userService.getAll();
    }
    @PostMapping("/createuser")
    public void createUser(@RequestBody User user)
    {
        userService.saveEntry(user);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName)
    {
        User userInDb = userService.findByUserName(userName);
        if(userInDb!=null)
        {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
