package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HashTagType {

    PROJECT_ENVIRONMENT("portfolio"),
    PROJECT_SKILL("portfolio");

    private final String table;

}
