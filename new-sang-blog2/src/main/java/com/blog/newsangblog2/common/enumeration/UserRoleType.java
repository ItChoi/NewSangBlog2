package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;

@Getter
public enum UserRoleType {
	SUPERVISOR("최고관리자", "ROLE_SUPERVISOR"),
	ADMIN("관리자", "ROLE_ADMIN"),
	MEMBER("고객", "ROLE_MEMBER"),
	ANONYMOUS("방문자", "ANONYMOUS");
	
	private String key;
	private String role;
	
	private UserRoleType(String key, String role) {
		this.key = key;
		this.role = role;
	}
	
}
