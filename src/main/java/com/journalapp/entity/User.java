package com.journalapp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

	@Id
	private ObjectId id;
	@Indexed(unique = true)
	@NonNull
	private String username;
	@NonNull
	private String password;
	
	private String email;
	private boolean sentimentAnalysis;
	
	@DBRef
	private List<JournalEntities> journalEntities=new ArrayList<>();
	
	private List<String> roles;
	
	
}
