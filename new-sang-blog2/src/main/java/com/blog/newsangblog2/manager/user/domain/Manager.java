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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
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
	
	@OneToMany(mappedBy = "manager")
	private List<UserRole> userRoles = new ArrayList<>();
	
	@Builder
	public Manager(String loginId, String password, String phoneNumber, String name, String introduce, String lastLoginDate, String createdLoginId, String updatedLoginId, String email, String imageFileName) {
		this.loginId = loginId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.introduce = introduce;
		this.lastLoginDate = lastLoginDate;
		this.email = email;
		this.imageFileName = imageFileName;
	}
	
}
