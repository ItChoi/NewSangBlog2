package com.blog.newsangblog2.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonPageDto {
    private int currentPage = 0;
    private int itemPerPage = 10;
}
