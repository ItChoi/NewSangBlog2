package com.blog.newsangblog2.manager.user.service;

import java.util.Optional;

import com.blog.newsangblog2.manager.user.domain.Manager;

public interface ManagerUserService {
	public abstract Optional<Manager> findByLoginId(String loginId);
}
