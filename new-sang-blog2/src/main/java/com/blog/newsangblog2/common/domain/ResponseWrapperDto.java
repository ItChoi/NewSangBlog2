package com.blog.newsangblog2.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class ResponseWrapperDto {
    private String code;
    private String message;
    private Collection list;
}
