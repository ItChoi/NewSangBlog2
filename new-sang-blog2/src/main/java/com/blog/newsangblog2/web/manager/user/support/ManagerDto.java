package com.blog.newsangblog2.web.manager.user.support;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
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
	private UserRoleDto userRole;

	private String phoneNumber1;
	private String phoneNumber2;
	private String phoneNumber3;

	public void settingPhonNumber() {
		String number = this.phoneNumber.replaceAll("[^0-9]", "");

		if (number.length() == 11) {
			this.phoneNumber1 = number.substring(0, 3);
			this.phoneNumber2 = number.substring(3, 7);
			this.phoneNumber3 = number.substring(7, 11);
		}
	}

	@Builder
	public ManagerDto(String loginId, String password, String name, String email) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.email = email;
	}


}
