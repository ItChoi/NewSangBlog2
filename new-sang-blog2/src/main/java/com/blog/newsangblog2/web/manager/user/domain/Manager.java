package com.blog.newsangblog2.web.manager.user.domain;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Manager extends BaseDateTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MANAGER_ID")
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String loginId;
	
	@Column(nullable = false, length = 100)
	private String password;

	@Column(length = 50)
	private String phoneNumber;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String email;
	
	@Column(length = 500)
	private String introduce;
	
	@Column(length = 14)
	private String lastLoginDate;
	
	@Column(length = 100)
	private String imageFileName;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ROLE_ID")
	private UserRole userRole;
	
	@Builder
	public Manager(String loginId, String password, String phoneNumber, String name, String introduce, String lastLoginDate, String createdLoginId, String updatedLoginId, String email, String imageFileName, UserRole userRole) {
		this.loginId = loginId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.introduce = introduce;
		this.lastLoginDate = lastLoginDate;
		this.email = email;
		this.imageFileName = imageFileName;
		this.userRole = userRole;
	}

	public Manager update(String name, String imageFileName) {
		this.name = name;
		this.imageFileName = imageFileName;

		return this;
	}

}
