//package com.blog.newsangblog2.common.domain;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.OneToMany;
//
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import com.blog.newsangblog2.manager.user.domain.UserRole;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@Getter
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public class BaseUserEntity extends BaseDateTimeEntity {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "USER_ID")
//	private Long id;
//	
//	@Column(nullable = false, length = 50)
//	private String loginId;
//	
//	@Column(nullable = false, length = 50)
//	private String password;
//	
//	@Column(nullable = false, length = 50)
//	private String phoneNumber;
//	
//	@Column(nullable = false, length = 50)
//	private String name;
//	
//	@Column(nullable = false, length = 500)
//	private String introduce;
//	
//	@Column(nullable = false, length = 14)
//	private String lastLoginDate;
//	
//	@Column(nullable = false)
//	private String imageUseStatus;
//	
//	public BaseUserEntity(String loginId, String password, String phoneNumber, String name, String introduce, String imageUse, String lastLoginDate, String createdLoginId, String updatedLoginId) {
//		this.loginId = loginId;
//		this.password = password;
//		this.phoneNumber = phoneNumber;
//		this.name = name;
//		this.introduce = introduce;
//		this.lastLoginDate = lastLoginDate;
//	}
//	
//}
