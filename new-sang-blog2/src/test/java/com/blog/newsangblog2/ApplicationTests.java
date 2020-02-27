package com.blog.newsangblog2;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.newsangblog2.common.enumeration.UserRoleType;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(UserRoleType.ADMIN.name());
	}

}
