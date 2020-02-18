package com.blog.newsangblog2.config.jasypt;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

@Configuration
@PropertySource("application.yml")
public class AppConfigForJasyptStarter {
	

}
