package com.blog.newsangblog2.common.enumeration;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum S3ImageUrl {

    //
    DIR_PROFILE("manager/user/profile");

    private final String url;

}
