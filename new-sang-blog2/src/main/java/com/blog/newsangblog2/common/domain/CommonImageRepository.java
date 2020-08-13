package com.blog.newsangblog2.common.domain;

import com.blog.newsangblog2.common.enumeration.CommonFileType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CommonImageRepository {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long refId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private CommonFileType fileType;

    private String fileName;

    private int ordering;


}
