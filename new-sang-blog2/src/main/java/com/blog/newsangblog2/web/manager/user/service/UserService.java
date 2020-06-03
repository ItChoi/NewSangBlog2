package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.common.exception.DuplicationException;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.common.utils.UserUtils;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.domain.UserRole;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final ManagerUserService managerUserService;

	private final ModelMapper modelMapper;

	private final ManagerUserRepository managerUserRepository;

	public Long createManager(ManagerDto managerDto) {
		Manager manager = modelMapper.map(managerDto, Manager.class);

		try {
			checkDuplicationValue(managerDto);

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			manager.setPassword(passwordEncoder.encode(managerDto.getPassword()));

			manager.setUserRole(
					UserRole.builder()
							.manager(manager)
							.authority(managerDto.getUserRole().getAuthority())
							.build()
			);

			managerUserRepository.save(manager);

		} catch (DuplicationException e) {
			log.info(e.getMessage());
		}

		return manager.getId();
	}

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
			authorities.add(new SimpleGrantedAuthority(managerInfo.getUserRole().getAuthority().getRole()));
		}
		
		return new User(managerInfo.getLoginId(), managerInfo.getPassword(), authorities);
	}

	public void checkDuplicationValue(ManagerDto managerDto) throws DuplicationException {
		if (io.micrometer.core.instrument.util.StringUtils.isNotEmpty(managerDto.getLoginId()) && managerUserRepository.existsByLoginId(managerDto.getLoginId())) {
			throw new DuplicationException("아이디: " + managerDto.getLoginId() + "은 이미 존재 합니다.");
		}

		if (io.micrometer.core.instrument.util.StringUtils.isNotEmpty(managerDto.getEmail()) && managerUserRepository.existsByEmail(managerDto.getEmail())) {
			throw new DuplicationException("이메일: " + managerDto.getEmail() + "은 이미 존재 합니다.");
		}
	}

	public Long updateManager(ManagerDto managerDto) {
		String loginId = UserUtils.getLoginId();
		Manager findManager = managerUserRepository.findManagerByLoginId(loginId).orElseThrow(() -> new UserNotFoundException(loginId + " 가 존재하지 않습니다."));

		findManager.setName(managerDto.getName());
		findManager.setPhoneNumber(managerDto.getPhoneNumber());
		findManager.setEmail(managerDto.getEmail());
		findManager.setIntroduce(managerDto.getIntroduce());

		return findManager.getId();
	}
}
