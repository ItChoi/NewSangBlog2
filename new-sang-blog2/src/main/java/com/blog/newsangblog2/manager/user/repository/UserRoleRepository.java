package com.blog.newsangblog2.manager.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.newsangblog2.manager.user.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	public Optional<UserRole> findUserRoleById(Long managerId);

}
