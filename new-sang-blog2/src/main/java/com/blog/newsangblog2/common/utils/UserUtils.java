package com.blog.newsangblog2.common.utils;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Optional;


public class UserUtils {
	
	public static String getLoginId() {
		// 스프링 시큐리티 세션 정보를 가져온다.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if ("anonymousUser".equals(auth.getPrincipal())) {
			return UserRoleType.ANONYMOUS.getRole();
		} else {
			User user = (User) auth.getPrincipal();
			return user.getUsername();
		}

	}

	public static boolean hasRole(UserRoleType role) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(role);
	}

}
