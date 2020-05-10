package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.common.exception.DuplicationException;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ManagerUserServiceImpl implements ManagerUserService {

	private final ManagerUserRepository managerUserRepository;

	@Override
	public Optional<Manager> findManagerBy(String loginId) {
		return managerUserRepository.findManagerByLoginId(loginId);
	}

}
