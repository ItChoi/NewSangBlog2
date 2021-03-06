package com.blog.newsangblog2.common.utils;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;


public class UserUtils {

	public static String getLoginId() {
		// 스프링 시큐리티 세션 정보를 가져온다.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) return null;

		if ("anonymousUser".equals(auth.getPrincipal())) {
			return UserRoleType.ANONYMOUS.getRole();
		} else {

			String userName = "";
			try {
				User user = (User) auth.getPrincipal();
				userName = user.getUsername();
			} catch (ClassCastException e) {
				// sns 로그인 시 가져오는 Auth는 형태가 좀 다르다. 밑에 hasRole도 수정 필요할듯
				DefaultOAuth2User user = (DefaultOAuth2User) auth.getPrincipal();
				userName = (String) user.getAttributes().get("email");
			}

			return userName;
		}
	}

	public static boolean hasRole(UserRoleType role) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(role);
	}

}
