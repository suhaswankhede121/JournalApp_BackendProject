package com.journalapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalapp.entity.ConfiJournalAppEntity;

public interface ConfigJournalAppRepo extends MongoRepository<ConfiJournalAppEntity, ObjectId> {

}
