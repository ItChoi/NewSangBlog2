package com.blog.newsangblog2.common.domain;

import com.blog.newsangblog2.common.enumeration.CommonFileType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class CommonImageRepository {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long refId;

    private String content;

    private CommonFileType fileType;

    private String fileName;

    private int ordering;


}
