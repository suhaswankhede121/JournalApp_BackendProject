package com.journalapp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.entity.JournalEntities;
import com.journalapp.entity.User;
import com.journalapp.repository.UserRepository;
import com.journalapp.service.JournalEntryService;
import com.journalapp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalController {

	@Autowired
	private JournalEntryService entryService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping
	public ResponseEntity<?> getAllEntriesOfUser (){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		User user=userService.findUserByUsername(username);
		List<JournalEntities> all=user.getJournalEntities();
		if(all!=null) {
			entryService.getAllEntry();
			return new ResponseEntity<>(all,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping
	public ResponseEntity<?> addEntries(@RequestBody JournalEntities je) {
		try {
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String username=auth.getName();
			entryService.saveEntry(je, username);
			return new ResponseEntity<>(je,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping("id/{myid}")
	public ResponseEntity<JournalEntities> getJournalById(@PathVariable String myid) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		User user=userService.findUserByUsername(username);
		List<JournalEntities> collects=user.getJournalEntities().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
		
		if(!collects.isEmpty()) {
			JournalEntities data=entryService.getEntityById(myid);
			if(data!=null) {
				return new ResponseEntity<>(data,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("id/{myid}")
	public ResponseEntity<?> deleteEntryById(@PathVariable String myid) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		userService.findUserByUsername(username);
			entryService.deleteById(myid,username);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PutMapping("/id/{myid}")
	public ResponseEntity<?> updateById(@RequestBody JournalEntities newdata, @PathVariable String myid) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		User user=userService.findUserByUsername(username);
       List<JournalEntities> collects=user.getJournalEntities().stream().filter(x->x.getId().equals(myid)).
    		   collect(Collectors.toList());
       
       
		if(!collects.isEmpty()) {
			JournalEntities old=entryService.getEntityById(myid);
			if (old != null) {
		        old.setTitle(newdata.getTitle() != null && !newdata.getTitle().isEmpty() ? newdata.getTitle() : old.getTitle());
		        old.setContent(newdata.getContent() != null && !newdata.getContent().isEmpty() ? newdata.getContent() : old.getContent());
		        entryService.saveEntry(old);
		        return new ResponseEntity<>(old,HttpStatus.NOT_FOUND);
		    } 
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    
	}

}
