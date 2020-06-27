package com.blog.newsangblog2.testcode;

import com.blog.newsangblog2.common.enumeration.PreNumber;
import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class TestCode {

	@Disabled
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
