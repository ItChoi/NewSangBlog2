package com.blog.newsangblog2.common.enumeration;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;

@Getter
@Setter
public class CommonImageRepositoryDto {

    private Long id;
    private Long refId;
    private String content;
    private CommonFileType fileType;
    private String fileName;
    private File file;
    private int ordering;
}
