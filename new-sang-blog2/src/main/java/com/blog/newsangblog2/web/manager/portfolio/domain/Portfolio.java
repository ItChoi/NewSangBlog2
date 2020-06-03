package com.blog.newsangblog2.web.manager.portfolio.domain;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Portfolio extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String url;

    private String zipFile;

    private String displayFlag;

    private String forceDisplayFlag;

}
