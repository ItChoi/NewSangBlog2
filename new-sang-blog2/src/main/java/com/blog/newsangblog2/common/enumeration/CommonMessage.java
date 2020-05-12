package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;

@Getter
public enum CommonMessage {
    ALREADY_EXISTS_ID("이미 존재하는 아이디입니다."),
    ID_IS_AVAILABLE("사용 가능한 아이디입니다.");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }
}
