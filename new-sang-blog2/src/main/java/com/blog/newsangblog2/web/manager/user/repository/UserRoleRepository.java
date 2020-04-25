package com.blog.newsangblog2.web.manager.user.repository;

import com.blog.newsangblog2.web.manager.user.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	public Optional<UserRole> findUserRoleById(Long managerId);

}
