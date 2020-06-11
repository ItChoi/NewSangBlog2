package com.blog.newsangblog2.testcode;

import java.util.Optional;

import com.blog.newsangblog2.common.enumeration.PreNumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestCode {

	@Test
	public void test() {
		String testLoginId = null;
		
		String loginId = Optional.ofNullable(testLoginId).orElseThrow(() -> new UserNotFoundException("세션에 등록된 아이디가 없습니다."));
		
		System.out.println("result: " + loginId);
		
	}
	
	@Test
	public void 이넘_테스트() {
		for (UserRoleType a : UserRoleType.values()) {
			String b = a.toString();
			System.out.println("result: " + b);
		}
	}

	@Test
	public void 이넘_테스트1() {

		for (String test : PreNumber.PHONE.getPreNumber()) {
			log.info("result::::::: " + test);
		}


	}
	
	@Test
	public void ROLE_확인() {
		System.out.println("test: " + UserRoleType.ADMIN.name());
	}

	@Test
	public void test123() {
		System.out.println("ASdasdasd");
	}
}
