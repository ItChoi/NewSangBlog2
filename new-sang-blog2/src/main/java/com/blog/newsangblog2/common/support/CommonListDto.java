package com.blog.newsangblog2.common.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonListDto extends CommonPageDto {

    // 검색 조건 - where 부분
    private String where;
    // 검색 조건 - 조건에 들어갈 부분
    private String query;



}
