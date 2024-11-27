package com.journalapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.journalapp.entity.JournalEntities;
import com.journalapp.entity.User;
import com.journalapp.repository.JournalEntriesRepo;

@Component
public class JournalEntryService {

	@Autowired
	private JournalEntriesRepo journalEntriesRepo;
	
	@Autowired
	private UserService userService;
	
	
	@Transactional
	public void saveEntry(JournalEntities je, String username) {
		try {
			User user=userService.findUserByUsername(username);
			JournalEntities data=journalEntriesRepo.save(je);
			user.getJournalEntities().add(data);
			userService.saveUser(user);
		}catch(Exception e) {
		  throw new RuntimeException("An error occured: "+e);
		}
		
	}
	
	public void saveEntry(JournalEntities je) {
		journalEntriesRepo.save(je);
	}
	
	public List<JournalEntities> getAllEntry(){
		return new ArrayList<>(journalEntriesRepo.findAll());
	}
	
	public JournalEntities getEntityById(String id) {
		return journalEntriesRepo.findById(id).orElse(null);
	}
	
	
	@Transactional
	public void deleteById(String id, String username) {
		try {
			User user=userService.findUserByUsername(username);
			boolean flag=user.getJournalEntities().removeIf(x->x.getId().equals(id));
			if(flag) {
				userService.saveUser(user);
				journalEntriesRepo.deleteById(id);
			}
		}catch(Exception e) {
			throw new RuntimeException("Error Occured during deletion");
		}
		
	} 
	
	public JournalEntities updateById(String id, JournalEntities je) {
		journalEntriesRepo.save(je);
		return je;
	}

	
	
}
