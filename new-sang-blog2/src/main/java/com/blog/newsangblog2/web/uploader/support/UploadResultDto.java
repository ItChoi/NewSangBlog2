package com.blog.newsangblog2.web.uploader.support;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadResultDto {
    private String path;
    private String name;
    private Long size;
}
