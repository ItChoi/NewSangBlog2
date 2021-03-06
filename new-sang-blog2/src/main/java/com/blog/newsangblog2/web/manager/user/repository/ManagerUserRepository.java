package com.blog.newsangblog2.web.manager.user.repository;

import com.blog.newsangblog2.web.manager.user.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerUserRepository extends JpaRepository<Manager, Long> {
	Optional<Manager> findManagerByLoginId(String loginId);
	boolean existsByLoginId(String loginId);
	boolean existsByEmail(String email);
	Optional<Manager> findByEmail(String email);
}
