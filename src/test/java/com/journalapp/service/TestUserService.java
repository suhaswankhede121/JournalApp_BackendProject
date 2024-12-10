package com.journalapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journalapp.repository.UserRepository;

@SpringBootTest
public class TestUserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"ram",
		"Suhas",
		"ansh",
		"shiva",
		"pratik"
	})
	public void testFindUserByUsername(String name) {
		assertNotNull(userRepository.findUserByUsername(name));
	}
	
	
	@Disabled
	@ParameterizedTest
	@CsvSource(
			{
			 "1,2,3",
			 "10,2,12",
			 "1,4,6"
			})
	public void testDemo(int a,int b,int exp) {
		assertEquals(exp, a+b);
	}
}
