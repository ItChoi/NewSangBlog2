package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;

import java.util.Optional;

public interface ManagerUserService {
	public Optional<Manager> findManagerBy(String loginId);
    public Long createManager(ManagerDto managerDto);
    public void checkDuplicationValue(ManagerDto managerDto);
}