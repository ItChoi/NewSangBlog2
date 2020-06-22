package com.blog.newsangblog2.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserInfo {
	LOGIN_ID("loginId"),
	EMAIL("email");
	
	private final String code;



}
