package com.blog.newsangblog2.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.AllArgsConstructor;

/**
 * @author itcho
 * @EnableWebSecurity
 * - @Configuration 클래스에 추가할 시 Spring Security 설정할 클래스라고 정의
 * - 설정은 WebSecurityConfigurerAdapter 클래스 상속 받아 메서드 구현이 일반적인 방법
 * 
 * WebSecurityConfigurerAdapter
 * - WebSecurityConfigurer 인스턴스를 편리하게 생성하기 위한 클래스
 * 
 * PasswordEncoder
 * - Spring Security에서 제공하는 비밀번호 암호화 객체
 * - Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * WebSecurity는 FilterChainProxy를 생성하는 필터
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉토리의 하위 파일 목록은 인증 무시 (항상 통과)
		web.ignoring().antMatchers(
			"/css/**", "/js/**", "/img/**", 
			"/lib/**", "/bootstrap/**", "/front/**",
			"/manager/**"
		);
	}
	
	/**
	 * HttpSecurity를 통해 HTTP 요청에 대한 웹 기반 보안을 구성할 수 있다.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// authorizeRequests: HttpServletRequest에 따라 접근(Access)을 제한
		http.authorizeRequests()
				// 페이지 권한 설정
				// antMatchers: 특정 경로 지정
				// hasRole: 역할에 따른 접근 설정
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/manager").permitAll()
				.antMatchers("/manager/**").hasRole("ADMIN")
				.antMatchers("user/myinfo").hasRole("MEMBER")
			.and()
				// 로그인 설정
				// form 기반으로 인증을 하고, 로그인 정보는 기본적으로 HttpSession을 이용
				// /login 경로로 접근하면, Spring Security에서 제공하는 로그인 form 사용 가능 
				.formLogin()
				// 기본 제공 form 말고, 커스텀 로그인 폼 사용을 원할 시 loginPage() 메서드 사용
				// 이 때 form의 action과 loginPage()의 경로가 일치해야 인증 처리 가능 (login.html)
				.loginPage("/manager/user/login")
				// 로그인 성공 시 이동되는 페이지
				.defaultSuccessUrl("/manager")
				.usernameParameter("loginId")
				// .usernameParameter("파라미터명") - 로그인 form에서 기본 적으로 name=username 이지만, 이를 통해 파라미터명 변경 가능
				.permitAll()
			.and()
				// 로그아웃 설정 WebSecurityConfigurerAdapter 사용 시 자동 적용
				// 기본적으로 /logout 에 접근하면 HTTP 세션 제거
				.logout()
				// 로그아웃 기본 URL을 다른 URL로 재정의
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/user/logout/result")
				// HTTP 세션 초기화 작업
				.invalidateHttpSession(true)
				// .deleteCookies("Key") - 로그아웃 시 특정 쿠키 제거하고 싶을 때 사용
			.and()
				// 403 예외 처리 핸들링
				// 예외 발생 시 이를 통해 핸들링 ex) 접근 권한 없을 시 로그인 페이지 이동
				.exceptionHandling()
				.accessDeniedPage("/user/denied");
	}
	
	/**
	 * Spring Security에서 모든 인증은 AuthenticationManager를 통해 이루어 진다.
	 * AuthenticationManager를 생성하기 위해서는 AuthenticationManagerBuilder를 사용
	 *  - 로그인 처리, 즉 인증을 위해서 UserDetailService를 통해 필요 정보를 가져온다.
	 *  - 서비스 클래스에서 UserDetailService 인터페이스를 implements하여 loadUserByUsername() 메소드 구현
	 * 비밀번호 암호화를 위해 passwordEncoder 사용
	 */
	/*
	 * @Override public void
	 * setAuthenticationConfiguration(AuthenticationConfiguration
	 * authenticationConfiguration) {
	 * 
	 * }
	 */


}
