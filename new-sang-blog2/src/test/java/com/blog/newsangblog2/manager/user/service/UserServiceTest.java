package com.blog.newsangblog2.manager.user.service;


import com.blog.newsangblog2.web.manager.user.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*@Disabled
	@Test
	public void test() {
		UserDetails userDetails = userService.loadUserByUsername("enffl18");
		String password = userDetails.getPassword();
		assertTrue(passwordEncoder.matches("qwe123", password));
	}*/
	
	@Test
	public void passwordEncoder_test() {
		PasswordEncoder passwordEncoder1 = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		String password1 = passwordEncoder1.encode("aaabbb123");
		String password2 = passwordEncoder.encode("aaabbb123");
		
		System.out.println("11111: " + password1);
		System.out.println("22222: " + password2);
	}
	
	

}
