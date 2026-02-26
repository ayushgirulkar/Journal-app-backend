package com.example.springpractice.repository;

import com.example.springpractice.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserReposiratory extends MongoRepository<User, ObjectId>
{
    User findByUserName(String username);

}
