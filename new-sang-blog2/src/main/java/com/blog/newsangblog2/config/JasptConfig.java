package com.blog.newsangblog2.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasptConfig {
	
	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		String salt = System.getProperty("spring.datasource");
		
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("passwordTest");
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(config);
		
		return encryptor;
		
	}

}
