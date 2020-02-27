package com.blog.newsangblog2.config.jasypt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service 
public class PropertyServiceForJasyptStarter {
	
	
	@Value("${spring.datasource.password}") 
	private String property;
	
	public String getProperty() { 
		return property; 
	}
  
	public String getPasswordUsingEnvironment(Environment env) { 
		return env.getProperty("spring.datasource.password"); 
	} 
}
 