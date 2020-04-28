package com.blog.newsangblog2.web.manager.user.domain;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;
import com.blog.newsangblog2.common.enumeration.UserRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class UserRole extends BaseDateTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ROLE_ID")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	private Manager manager;
	
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private UserRoleType authority;

}
