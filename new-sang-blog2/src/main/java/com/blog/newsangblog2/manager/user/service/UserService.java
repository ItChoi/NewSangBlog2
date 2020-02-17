package com.blog.newsangblog2.manager.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.repository.ManagerRepository;

import lombok.AllArgsConstructor;

/**
 * 스프링 시큐리티 서비스 클래스
 * @author itcho
 *
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	
	private ManagerRepository managerRepository;

	/**
	 * 상세 정보 조회 메서드
	 *  - 사용자의 계정 정보와 권한을 갖는 UserDetails 인터페이스 반환
	 *  - 매개 변수는 로그인 시 입력한 아이디
	 *  - 기본 값은 form의 name="username"으로 요청 (username은 커스터마이징 가능)
	 *  - authorities.add(new SimpleGrantedAuthority());
	 *    - 롤을 부여하는 코드
	 *  - new User()
	 *    - UserDetails를 구현한 User 반환 
	 */
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		Optional<Manager> managerInfo = managerRepository.findByLoginId(loginId);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (managerInfo.isPresent()) {
			
			
			
			
		}
		
		// Manager manager = Optional.ofNullable(value)
		return null;
	}

}
