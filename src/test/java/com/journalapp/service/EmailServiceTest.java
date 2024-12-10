package com.journalapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTest {

	
	@Autowired
	private EmailServices emailServices;
	
	
	@Test
	public void testSendMail() {
		emailServices.sendMail("officialwankhede25@gmail.com", "From spring boot", "KLaise ho");
	}
}
