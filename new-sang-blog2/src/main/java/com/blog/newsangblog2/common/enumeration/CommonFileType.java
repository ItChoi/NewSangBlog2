package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonFileType {

    PORTFOLIO("portfolio");

    private final String tableName;


}
