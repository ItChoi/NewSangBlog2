package com.blog.newsangblog2.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonMessage {
    ALREADY_EXISTS_ID("이미 존재 하는 아이디 입니다."),
    ID_IS_AVAILABLE("사용 가능한 아이디 입니다.");

    private String message;

}
