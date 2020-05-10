package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.common.exception.DuplicationException;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;

import java.util.Optional;

public interface ManagerUserService {
	Optional<Manager> findManagerBy(String loginId);
}
