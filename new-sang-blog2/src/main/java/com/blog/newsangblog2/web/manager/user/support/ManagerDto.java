package com.blog.newsangblog2.web.manager.user.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
	private Long id;
	private String loginId;
	private String password;
	private String phoneNumber;
	private String name;
	private String email;
	private String introduce;
	private String lastLoginDate;
	private String imageFileName;
	private MultipartFile file;
	//private UserRole userRole;
	//private UserRoleType authority;
	private UserRoleDto userRole;

}
