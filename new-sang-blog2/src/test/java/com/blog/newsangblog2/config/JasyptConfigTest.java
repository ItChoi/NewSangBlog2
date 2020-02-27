package com.blog.newsangblog2.config;

import static org.junit.Assert.assertEquals;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.newsangblog2.config.jasypt.PropertyServiceForJasyptStarter;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptConfigTest {
	
	@Autowired
	ApplicationContext appCtx;
	
	
	@Test
	public void test() {
		// TODO::: 깃 캐시 삭제하고 다시 푸시 필요
		
		  StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		  jasypt.setPassword("m2sj"); 
		  jasypt.setAlgorithm("PBEWithMD5AndDES");
		  String encryptPassword = jasypt.encrypt("Tkdgus1818"); 
		  String originPassword = jasypt.decrypt(encryptPassword); 
		  System.out.println("encryptPassword: " + encryptPassword); 
		  System.out.println("originPassword: " + originPassword);
		 
	}
	
	@Test
	public void 테스트() {
		Environment env = appCtx.getBean(Environment.class);
		System.out.println("------");
		String a = env.getProperty("encryptedv3.property");
		System.out.println("------@@@");
		System.out.println("111: " + env.getProperty("encryptedv3.property"));
		assertEquals("itchoi0429", env.getProperty("encryptedv3.property"));
		System.out.println("222");
	}
	
	@Test
	public void 테스트1() {
		System.out.println("---000!!!");
		// System.setProperty("jasypt.encryptor.password", "m2sj");
		// PropertyServiceForJasyptStarter service = appCtx.getBean(PropertyServiceForJasyptStarter.class);
		// assertEquals("asdasdasd", service.getProperty());
		
		Environment env = appCtx.getBean(Environment.class);
		assertEquals("asdasasd", env.getProperty("spring.datasource.password"));
		System.out.println("---111!!!");
		System.out.println("---222!!!");
	}

}
