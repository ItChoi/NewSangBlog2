package com.blog.newsangblog2.manager.user.support;

import com.blog.newsangblog2.manager.user.domain.Manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ManagerDto {
	private String loginId;
	private String password;
	private String phoneNumber;
	private String name;
	private String introduce;
	private String lastLoginDate;
	private String email;
	private String imageFileName;
	private MultipartFile file;
	
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
