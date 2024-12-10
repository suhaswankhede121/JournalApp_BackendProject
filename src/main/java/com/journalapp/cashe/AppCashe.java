package com.journalapp.cashe;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalapp.entity.ConfiJournalAppEntity;
import com.journalapp.repository.ConfigJournalAppRepo;

import jakarta.annotation.PostConstruct;

@Component
public class AppCashe {
	
	public enum keys{
			WEATHER_API;
	}

	@Autowired
	private ConfigJournalAppRepo configJournalAppRepo;
	
	public HashMap<String, String> appCashe;
	
	
	
	@PostConstruct
	public void init() {
		appCashe=new HashMap<String, String>();
		List<ConfiJournalAppEntity> all=configJournalAppRepo.findAll();
		 for(ConfiJournalAppEntity confiJournalAppEntity:all) {
			 appCashe.put(confiJournalAppEntity.getKey(), confiJournalAppEntity.getValue());
		 }
	}
}
