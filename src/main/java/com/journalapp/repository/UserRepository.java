package com.journalapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalapp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findUserByUsername(String username); // Correct property name is "username"
    void deleteByUsername(String username);  // Correct property name is "username"
}
