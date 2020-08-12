package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HashTagType {

    PROJECT_ENVIRONMENT("portfolio", "env"),
    PROJECT_SKILL("portfolio", "skill");

    private final String table;
    private final String type;

}
