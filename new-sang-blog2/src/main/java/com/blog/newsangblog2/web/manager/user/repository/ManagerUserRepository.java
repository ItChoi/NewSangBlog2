package com.blog.newsangblog2.web.manager.user.repository;

import com.blog.newsangblog2.web.manager.user.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerUserRepository extends JpaRepository<Manager, Long> {
	public Optional<Manager> findManagerByLoginId(String loginId);
	public boolean existsByLoginId(String loginId);
	public boolean existsByEmail(String email);
}
