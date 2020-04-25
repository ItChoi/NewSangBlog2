package com.blog.newsangblog2.web.manager.user.support;

import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.domain.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ManagerDto {
	private String loginId;
	private String password;
	private String phoneNumber;
	private String name;
	private String email;
	private String introduce;
	private String lastLoginDate;
	private String imageFileName;
	private MultipartFile file;
	private List<UserRole> userRoles = new ArrayList<>();

	
	public Manager toEntity() {
		return Manager.builder()
				.loginId(loginId)
				.password(password)
				.phoneNumber(phoneNumber)
				.name(name)
				.introduce(introduce)
				.lastLoginDate(lastLoginDate)
				.email(email)
				.imageFileName(imageFileName)
				.build();
	}
}
