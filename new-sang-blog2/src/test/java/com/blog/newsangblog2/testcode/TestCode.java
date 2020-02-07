package com.blog.newsangblog2.testcode;

import java.util.Optional;

import org.junit.Test;

import com.blog.newsangblog2.exception.UserNotFoundException;

public class TestCode {

	@Test
	public void test() {
		String testLoginId = null;
		
		String loginId = Optional.ofNullable(testLoginId).orElseThrow(() -> new UserNotFoundException("세션에 등록된 아이디가 없습니다."));
		
		System.out.println("result: " + loginId);
		
	}
}
