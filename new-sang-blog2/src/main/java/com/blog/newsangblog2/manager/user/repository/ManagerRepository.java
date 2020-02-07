package com.blog.newsangblog2.manager.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.newsangblog2.manager.user.domain.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
	
}
