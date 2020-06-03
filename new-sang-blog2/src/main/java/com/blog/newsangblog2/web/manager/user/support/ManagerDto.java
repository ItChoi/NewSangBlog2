package com.blog.newsangblog2.web.manager.user.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


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

}
