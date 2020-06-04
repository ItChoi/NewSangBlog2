package com.blog.newsangblog2.common.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ResponseWrapperDto {
    private String code;
    private String message;
    private List list;
    private Pagination pagination;


}
