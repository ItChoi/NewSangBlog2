package com.blog.newsangblog2.utils;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.blog.newsangblog2.common.enumeration.UserInfo;
import com.blog.newsangblog2.exception.UserNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserUtils {
	
	public static String getLoginId() {
		HttpSession session = getHttpServletRequest().getSession();
		return Optional.ofNullable(
					(String) session.getAttribute(UserInfo.LOGIN_ID.getLoginId())
				).orElseThrow(() -> new UserNotFoundException("세션에 등록된 아이디가 없습니다."));
	}
	
	private static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return sra.getRequest();
	}
}
