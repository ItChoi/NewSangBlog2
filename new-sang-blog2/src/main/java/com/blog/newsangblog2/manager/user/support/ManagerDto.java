package com.blog.newsangblog2.manager.user.support;

import com.blog.newsangblog2.manager.user.domain.Manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	 
	private String createdLoginId;
	private String updatedLoginId;
	
	/*
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
	 * @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	 * @JsonSerialize(using = LocalDateTimeSerializer.class) private LocalDateTime
	 * createdDate;
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
	 * @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	 * @JsonSerialize(using = LocalDateTimeSerializer.class) private LocalDateTime
	 * updatedDate;
	 */
	
	public Manager toEntity() {
		return Manager.builder()
				.loginId(loginId)
				.password(password)
				.phoneNumber(phoneNumber)
				.name(name)
				.introduce(introduce)
				.lastLoginDate(lastLoginDate)
				.createdLoginId(createdLoginId)
				.updatedLoginId(updatedLoginId)
				.build();
	}
}
