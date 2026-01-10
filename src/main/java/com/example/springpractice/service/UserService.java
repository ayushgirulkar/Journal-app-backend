package com.example.springpractice.service;

import com.example.springpractice.entity.JournalEntry;
import com.example.springpractice.entity.User;
import com.example.springpractice.reposiratory.JournalEntryRepository;
import com.example.springpractice.reposiratory.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService
{
@Autowired
    private UserRepository userRepository;

private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();// i made instance here

public void saveEntry(User user)
{
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    user.setRoles(Arrays.asList("USER"));
}
    public void saveNewUser(User user)
    {
        userRepository.save(user);
    }
public List<User>getAll()
{
    return userRepository.findAll();
}
public Optional<User> findById(ObjectId id)
{
    return userRepository.findById(id);
}
public boolean deleteById(ObjectId id)
{   userRepository.deleteById(id);
    return true;
}
public User findByUserName(String userName)
{
    return userRepository.findByUserName(userName);
}


}
