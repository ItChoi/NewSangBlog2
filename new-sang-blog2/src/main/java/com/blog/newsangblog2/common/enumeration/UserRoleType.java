package com.blog.newsangblog2.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleType {
	SUPERVISOR("최고 관리자", "ROLE_SUPERVISOR"),
	ADMIN("관리자", "ROLE_ADMIN"),
	MEMBER("회원", "ROLE_MEMBER"),
	ANONYMOUS("방문자", "ANONYMOUS");
	
	private String key;
	private String role;
	
}
