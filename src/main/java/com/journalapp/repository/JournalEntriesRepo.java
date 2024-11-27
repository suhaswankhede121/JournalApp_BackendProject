package com.journalapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalapp.entity.JournalEntities;

public interface JournalEntriesRepo extends MongoRepository<JournalEntities, String>{

}
