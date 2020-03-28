package com.blog.newsangblog2.testcode;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.exception.UserNotFoundException;

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
	public void ROLE_확인() {
		System.out.println("test: " + UserRoleType.ADMIN.name());
	}
	
}
