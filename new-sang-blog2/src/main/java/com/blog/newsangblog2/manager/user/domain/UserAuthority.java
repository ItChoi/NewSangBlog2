package com.blog.newsangblog2.manager.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserAuthority extends BaseDateTimeEntity {
	
	@Id
	@Column(nullable = false, length = 30)
	private String authority;
	
	@Column(length = 30)
	private String authorityName;

	
}
