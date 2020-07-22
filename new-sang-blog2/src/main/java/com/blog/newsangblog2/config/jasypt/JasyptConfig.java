package com.blog.newsangblog2.config.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
	
	@Bean("jasyptEncryptorBean")
	public StringEncryptor stringEncryptor() {
		
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		// setPassword도 암호화 필요 -> VM Arguments를 이용하던가 등등등..
		config.setPassword("m2sj");
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
