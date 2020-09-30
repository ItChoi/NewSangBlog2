package com.blog.newsangblog2;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	void contextLoads() {
		System.out.println(UserRoleType.ADMIN.name());
	}

}
