package com.blog.newsangblog2.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.newsangblog2.config.jasypt.AppConfigForJasyptStarter;
import com.blog.newsangblog2.config.jasypt.PropertyServiceForJasyptStarter;

public class PropertyServiceForJasptStarterTest {
	
	// @Autowired AnnotationConfigApplicationContext ctx;
	// @Autowired AnnotationConfigWebApplicationContext ctx;
//	@Autowired GenericApplicationContext ctx;
//	@Autowired GenericWebApplicationContext ctx;
	//@Autowired WebApplicationContext ctx;
	//@Autowired ConfigurableApplicationContext ctx;
//	@Autowired ApplicationContext ctx;
	//@Autowired AnnotationConfigApplicationContext ctx;
	
	@Autowired ConfigurableApplicationContext ctx;
	
	@Test
	public void test() {
		System.setProperty("datasource.url", "password");
		System.setProperty("test.value", "55555555");
		
		// ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigForJasypt.class);
		// PropertyServiceForJasypt service = (PropertyServiceForJasypt) ctx.getBean(PropertyServiceForJasypt.class);
		
		//PropertyServiceForJasyptStarter service = ctx.getBean(PropertyServiceForJasyptStarter.class);
		//System.out.println("service: " + service);
		
		//assertEquals("Password@1", service.getPassword());
		
		
		
		
	}

}
