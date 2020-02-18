package com.blog.newsangblog2.manager.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.repository.ManagerUserRepository;

@Service
public class ManagerUserServiceImpl implements ManagerUserService {
	
	@Autowired
	ManagerUserRepository managerUserRepository;

	@Override
	public Optional<Manager> findByLoginId(String loginId) {
		return managerUserRepository.findByLoginId(loginId);
	}
	
	

}
