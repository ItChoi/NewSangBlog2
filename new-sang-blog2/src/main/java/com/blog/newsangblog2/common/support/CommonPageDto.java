package com.blog.newsangblog2.common.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonPageDto {
    private int page = 0;
    private int size = 10;
}
