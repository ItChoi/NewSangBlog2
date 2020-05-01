package com.blog.newsangblog2.web.manager.user.support;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


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
	private UserRoleType authority;

}
