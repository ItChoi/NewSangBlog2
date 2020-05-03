package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 스프링 시큐리티 서비스 클래스
 * @author itcho
 *
 */
@Transactional
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final ManagerUserService managerUserService;

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
		Manager managerInfo = managerUserService.findManagerBy(loginId)
								.orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (!StringUtils.isEmpty(managerInfo)) {
			
			/*List<UserRole> userRoleList= managerInfo.getUserRoles();
			userRoleList.stream().forEach(
				role -> authorities.add(new SimpleGrantedAuthority(role.getAuthority().getRole()))
			);*/

			authorities.add(new SimpleGrantedAuthority(managerInfo.getUserRole().getAuthority().getRole()));

		}
		
		return new User(managerInfo.getLoginId(), managerInfo.getPassword(), authorities);
	}

}