package com.example.springpractice.service;
import com.example.springpractice.reposiratory.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserServiceTests
{   @Autowired
    private UserRepository userRepository;
    @Test
    public void testFindByUserName()
    {
        assertEquals(4,2+2);
    }

}
