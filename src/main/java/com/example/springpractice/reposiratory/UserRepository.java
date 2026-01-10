package com.example.springpractice.reposiratory;

import com.example.springpractice.entity.JournalEntry;
import com.example.springpractice.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, ObjectId>
{
    User findByUserName(String username);

    void deleteByUserName(String username);
}

