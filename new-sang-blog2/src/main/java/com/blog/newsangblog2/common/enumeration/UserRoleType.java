package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleType {
	SUPERVISOR("최고 관리자", "ROLE_SUPERVISOR"),
	ADMIN("관리자", "ROLE_ADMIN"),
	MEMBER("회원", "ROLE_MEMBER"),
	ANONYMOUS("방문자", "ANONYMOUS");
	
	private final String key;
	private final String role;
	
}
