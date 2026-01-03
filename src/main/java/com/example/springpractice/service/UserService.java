package com.example.springpractice.service;

import com.example.springpractice.entity.JournalEntry;
import com.example.springpractice.entity.User;
import com.example.springpractice.reposiratory.JournalEntryRepository;
import com.example.springpractice.reposiratory.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService
{
@Autowired
    private UserRepository userRepository;

public void saveEntry(User user)
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
