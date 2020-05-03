package com.blog.newsangblog2.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserInfo {
	LOGIN_ID("loginId"),
	EMAIL("email");
	
	private String code;



}
