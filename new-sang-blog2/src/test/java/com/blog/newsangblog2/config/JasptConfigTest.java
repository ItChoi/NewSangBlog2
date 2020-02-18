package com.blog.newsangblog2.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

public class JasptConfigTest {
	
	@Test
	public void test() {
		// TODO::: 깃 캐시 삭제하고 다시 푸시 필요
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword("m2sj");
		jasypt.setAlgorithm("PBEWithMD5AndDES");
		
		String encryptPassword = jasypt.encrypt("m2sj");
		String originPassword = jasypt.decrypt(encryptPassword);
		System.out.println("encryptPassword: " + encryptPassword);
		System.out.println("originPassword: " + originPassword);
		
		
		
		
	}

}
