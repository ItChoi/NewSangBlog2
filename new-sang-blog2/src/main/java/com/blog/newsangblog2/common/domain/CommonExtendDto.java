package com.blog.newsangblog2.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonExtendDto {

    // TODO::: 페이징 관련 변수 추가

    // 검색 조건 - where 부분
    private String where;
    // 검색 조건 - 조건에 들어갈 부분
    private String query;

}
