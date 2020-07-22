package com.blog.newsangblog2.web.manager.user.domain;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserAuthority extends BaseDateTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String authority;
	
	@Column(length = 30)
	private String authorityName;

	
}
