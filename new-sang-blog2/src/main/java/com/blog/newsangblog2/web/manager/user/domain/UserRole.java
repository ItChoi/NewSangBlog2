package com.blog.newsangblog2.web.manager.user.domain;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;
import com.blog.newsangblog2.common.enumeration.UserRoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class UserRole extends BaseDateTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ROLE_ID")
	private Long id;
	
	@OneToOne(mappedBy = "userRole", fetch = FetchType.LAZY)
	private Manager manager;

	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private UserRoleType authority;

	@Builder
	public UserRole(Manager manager, UserRoleType authority) {
		this.manager = manager;
		this.authority = authority;
	}

}
