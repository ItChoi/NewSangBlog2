package com.blog.newsangblog2.common.enumeration;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileRoute {

    DIR_PROFILE("manager/user/profile"),
    PROFILE("profile/"),
    PORTFOLIO("portfolio/");

    private final String route;

}
