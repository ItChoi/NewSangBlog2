package com.blog.newsangblog2.common.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BaseUserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String loginId;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String introduce;
	
	@Column(nullable = false)
	private String imageUseStatus;
	
	@Column(nullable = false)
	private String lastLoginDate;
	
}
