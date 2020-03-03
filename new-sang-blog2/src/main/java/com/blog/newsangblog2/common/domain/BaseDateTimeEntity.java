package com.blog.newsangblog2.common.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
// JPA @Entity들이 BaseDateTimeEntity를 상속할 경우 필드들을 컬럼으로 인식하도록 설정
@MappedSuperclass
// BaseDateTimeEntity 클래스에 Auditing 기능 포함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDateTimeEntity {
	
	@CreatedBy
	private String createdLoginId;
	
	@CreatedBy
	private String updatedLoginId;

	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	
	
}
