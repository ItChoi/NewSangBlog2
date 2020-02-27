package com.blog.newsangblog2.manager.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class UserRole extends BaseDateTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ROLE_ID")
	private Long id;
	
	@Column(name = "USER_ID")
	private Long userId;

	@Column(length = 30)
	private String authority;

	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	private Manager manager;
	
}
