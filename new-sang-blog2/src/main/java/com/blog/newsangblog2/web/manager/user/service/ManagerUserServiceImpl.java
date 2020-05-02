package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.domain.UserRole;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
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

	private final ModelMapper modelMapper;

	@Override
	public Optional<Manager> findManagerBy(String loginId) {
		return managerUserRepository.findManagerByLoginId(loginId);
	}

	@Transactional
	@Override
	public Long createManager(ManagerDto managerDto) {
		Manager manager = modelMapper.map(managerDto, Manager.class);
		manager.setUserRole(
				UserRole.builder()
					.manager(manager)
					.authority(manager.)
		);

		managerUserRepository.save(manager);

		return manager.getId();
	}


}
