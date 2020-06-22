package com.blog.newsangblog2.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceType {

    DIRECTORY("file"),
    FILE("file");

    private final String type;

}
