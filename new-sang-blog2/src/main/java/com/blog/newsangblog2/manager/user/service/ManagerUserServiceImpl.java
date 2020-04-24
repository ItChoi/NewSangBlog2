package com.blog.newsangblog2.manager.user.service;

import java.util.Optional;

import com.blog.newsangblog2.manager.user.support.ManagerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.domain.UserRole;
import com.blog.newsangblog2.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.manager.user.repository.UserRoleRepository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ManagerUserServiceImpl implements ManagerUserService {
	
	private final ManagerUserRepository managerUserRepository;

	@Override
	public Optional<Manager> findManagerBy(String loginId) {
		return managerUserRepository.findManagerByLoginId(loginId);
	}

	@Transactional
	@Override
	public Long createManager(ManagerDto managerDto) {


		managerUserRepository.save()

		return null;
	}
}
