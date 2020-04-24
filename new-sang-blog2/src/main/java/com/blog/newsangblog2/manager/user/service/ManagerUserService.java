package com.blog.newsangblog2.manager.user.service;

import java.util.Optional;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.domain.UserRole;
import com.blog.newsangblog2.manager.user.support.ManagerDto;

public interface ManagerUserService {
	public Optional<Manager> findManagerBy(String loginId);
    public Long createManager(ManagerDto managerDto);
}
