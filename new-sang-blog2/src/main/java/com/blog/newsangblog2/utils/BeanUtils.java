package com.blog.newsangblog2.utils;

import org.springframework.context.ApplicationContext;

import com.blog.newsangblog2.config.bean.ApplicationContextProvider;

public class BeanUtils {
	public static Object getBean(String beanName) {
		ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
		return ctx.getBean(beanName);
	}
}
