package com.journalapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.journalapp.entity.User;

public class UserRepositoryImpl {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> getAllUserForSA(){
		Query query=new Query();
		
		
		query.addCriteria(Criteria.where("email").regex("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$"));
		//query.addCriteria(Criteria.where("email").ne("").and(null));
		query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
		
		List<User> users=mongoTemplate.find(query, User.class);
		return users;
		
	}
}
