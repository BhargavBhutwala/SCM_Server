package com.scm.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.scm.services.EmailService;

@SpringBootTest
class ScmApplicationTests {

	@Autowired
	private EmailService emailService;

	@Test
	void sendEmailTest() {

		emailService.sendEmail("bhargav1999.bb@gmail.com", "Test Email", "Email email is for testing purposes");
	}

}
