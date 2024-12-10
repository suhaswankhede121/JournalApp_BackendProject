package com.journalapp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
public class ConfiJournalAppEntity {

	private String key;
	private String value;
	
}
