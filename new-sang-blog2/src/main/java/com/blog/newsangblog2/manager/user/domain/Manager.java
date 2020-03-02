package com.blog.newsangblog2.manager.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Manager extends BaseDateTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MANAGER_ID")
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String loginId;
	
	@Column(nullable = false, length = 50)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String phoneNumber;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 500)
	private String introduce;
	
	@Column(nullable = false, length = 14)
	private String lastLoginDate;
	
	@Column(nullable = false)
	private String imageUseStatus;
	
	@OneToMany(mappedBy = "manager")
	private List<UserRole> userRoles = new ArrayList<>();
	
	@Builder
	public Manager(String loginId, String password, String phoneNumber, String name, String introduce, String imageUse, String lastLoginDate, String createdLoginId, String updatedLoginId) {
		this.loginId = loginId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.introduce = introduce;
		this.lastLoginDate = lastLoginDate;
	}
	
}
