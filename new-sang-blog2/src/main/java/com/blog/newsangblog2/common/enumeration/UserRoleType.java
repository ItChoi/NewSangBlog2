package com.blog.newsangblog2.common.enumeration;

public enum UserRoleType {
	ADMIN("관리자", "ROLE_ADMIN"),
	MEMBER("고객", "ROLE_MEMBER"),
	ANONYMOUS("방문자", "ANONYMOUS");
	
	private String key;
	private String value;
	
	private UserRoleType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
}
