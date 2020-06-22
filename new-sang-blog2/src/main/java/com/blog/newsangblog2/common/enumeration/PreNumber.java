package com.blog.newsangblog2.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PreNumber {
    PHONE(new String[]{"010", "011", "016", "017", "019"}, null),
    TEL(new String[]{"02", "031", "070"}, new String[]{"서울", "강원도", "인터넷"});

    private final String[] preNumber;
    private final String[] city;

}
