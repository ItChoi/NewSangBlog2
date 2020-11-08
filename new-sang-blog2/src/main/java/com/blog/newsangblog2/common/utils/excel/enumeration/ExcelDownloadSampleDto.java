package com.blog.newsangblog2.common.utils.excel.enumeration;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public enum ExcelDownloadSampleDto {
    ID("아이디", "id", ""),
    LOGIN_ID("로그인 아이디", "loginId", ""),
    PASSWORD("비밀번호", "password", ""),
    PHONE_NUMBER("핸드폰 번호", "phoneNumber", ""),
    NAME("이름", "name", ""),
    EMAIL("이메일", "email", ""),
    INTRODUCE("소개", "introduce", ""),
    LAST_LOGIN_DATE("마지막 로그인 날짜", "lastLoginDate", ""),
    IMAGE_FILE_NAME("이미지 파일 이름", "imageFileName", "");


    private final String hanText;
    private final String enText;
    private final String code;

    public static Map convertListToMap(List<String> head) {
        Map<String, String> headInfo = new HashMap<>();

        for (String field : head) {
            Arrays.stream(values())
                    .forEach(val -> {
                        if (field.equals(val.hanText)) {
                            headInfo.put(val.enText, field);
                            return;
                        }
                    });
        }

        return headInfo;
    }

}
