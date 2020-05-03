package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.common.enumeration.UserInfo;
import com.blog.newsangblog2.common.exception.DuplicationException;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.domain.UserRole;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.web.manager.user.repository.UserRoleRepository;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ManagerUserServiceImpl implements ManagerUserService {

	private final ManagerUserRepository managerUserRepository;

	private final UserRoleRepository userRoleRepository;

	private final ModelMapper modelMapper;

	@Override
	public Optional<Manager> findManagerBy(String loginId) {
		return managerUserRepository.findManagerByLoginId(loginId);
	}

	@Transactional
	@Override
	public Long createManager(ManagerDto managerDto) {

		checkDuplicationValue(managerDto);

		Manager manager = modelMapper.map(managerDto, Manager.class);
		UserRole userRole = new UserRole();
		userRole.setManager(manager);
		userRole.setAuthority(manager.getUserRole().getAuthority());
		manager.setUserRole(userRole);
		userRoleRepository.save(userRole);
		managerUserRepository.save(manager);

		return manager.getId();
	}

	public void checkDuplicationValue(ManagerDto managerDto) {
		if (StringUtils.isNotEmpty(managerDto.getLoginId()) && managerUserRepository.existsByLoginId(managerDto.getLoginId())) {
			throw new DuplicationException("아이디: " + managerDto.getLoginId() + "은 이미 존재 합니다.");
		}

		if (StringUtils.isNotEmpty(managerDto.getEmail()) && managerUserRepository.existsByEmail(managerDto.getEmail())) {
			throw new DuplicationException("이메일: " + managerDto.getEmail() + "은 이미 존재 합니다.");
		}
	}


}
