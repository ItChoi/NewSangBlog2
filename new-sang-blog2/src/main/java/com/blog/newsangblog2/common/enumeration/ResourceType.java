package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;

@Getter
public enum ResourceType {

    DIRECTORY("file"),
    FILE("file");

    private String type;

    ResourceType(String type) {
        this.type = type;
    }

}
