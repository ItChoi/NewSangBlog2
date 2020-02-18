package com.blog.newsangblog2.manager.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.newsangblog2.manager.user.domain.Manager;

public interface ManagerUserRepository extends JpaRepository<Manager, Long> {
	public Optional<Manager> findByLoginId(String loginId);
}
