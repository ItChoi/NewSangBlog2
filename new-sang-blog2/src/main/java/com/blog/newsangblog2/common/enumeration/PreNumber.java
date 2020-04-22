package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;

@Getter
public enum PreNumber {
    PHONE(new String[]{"010", "011", "016", "017", "019"}, null),
    TEL(new String[]{"02", "031", "070"}, new String[]{"서울", "강원도", "인터넷"});

    String[] preNumber;
    String[] city;

    PreNumber(String[] preNumber, String[] city) {
        this.preNumber = preNumber;
        this.city = city;
    }
}
