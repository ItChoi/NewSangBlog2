package com.blog.newsangblog2.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class JasptConfigTest {

	@Test
	public void test() {
		/**
			config.setPassword("passwordTest");
			config.setAlgorithm("PBEWithMD5AndDES");
			config.setKeyObtentionIterations("1000");
			config.setPoolSize("1");
			config.setProviderName("SunJCE");
			config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
			config.setStringOutputType("base64");
			encryptor.setConfig(config);
		 */
		
		
		StandardPasswordEncoder asd = new StandardPasswordEncoder();
		// BCryptPasswordEncoder asd = new BCryptPasswordEncoder();
		
		
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword("m2sj");
		jasypt.setAlgorithm("PBEWithMD5AndDES");
		
		String encryptPassword = jasypt.encrypt("testPassword");
		String originPassword = jasypt.decrypt(encryptPassword);
		
		System.out.println("encryptPassword: " + encryptPassword);
		System.out.println("originPassword: " + originPassword);
	}

}
