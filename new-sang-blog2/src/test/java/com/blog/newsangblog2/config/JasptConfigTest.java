package com.blog.newsangblog2.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.blog.newsangblog2.config.jasypt.JasyptConfig;


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
	
	@Test
	public void 테스트() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.refresh();
		Environment env = ctx.getBean(Environment.class);
		
		
		System.out.println("result: " + env.getProperty("test.value"));
	}

}
