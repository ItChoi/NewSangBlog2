package com.blog.newsangblog2.manager.user.service;

import java.util.Optional;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.domain.UserRole;

public interface ManagerUserService {
	public abstract Optional<Manager> findManagerBy(String loginId);
}
