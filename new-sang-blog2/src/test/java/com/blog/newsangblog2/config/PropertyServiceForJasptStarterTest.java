package com.blog.newsangblog2.config;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PropertyServiceForJasptStarterTest {
	
	@Autowired
	private ApplicationContext appCtx;

	@Autowired
	private ApplicationContext ctx;
	
	@Test
	public void test() {
		System.setProperty("jasypt.encryptor.password", "password");
		
		
		
		
		// PropertyServiceForJasptStarter service service = appCtx.get
		
	}

}
