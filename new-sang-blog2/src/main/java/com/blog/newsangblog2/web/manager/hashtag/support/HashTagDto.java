package com.blog.newsangblog2.web.manager.hashtag.support;

import com.blog.newsangblog2.common.enumeration.HashTagType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HashTagDto {
    private Long id;
    private Long refId;
    private String title;
    private HashTagType hashTagType;
}
