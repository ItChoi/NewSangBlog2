package com.blog.newsangblog2.manager.menu.domain;

import com.blog.newsangblog2.common.enumeration.ResourceType;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class ManagerMenu {

    @Id @GeneratedValue
    private Long id;

    @Column
    private Long parentId;

    @Column(length = 10)
    private String menuLevel;

    @Column(length = 100)
    private String menuCode;

    @Column(length = 100)
    private String menuName;

    @Column
    private int ordering;

    @Column(length = 100)
    private String url;

    @Column(length = 100)
    private String uri;

    @Column(length = 1)
    private String menuDisplay;

    @Enumerated(EnumType.STRING)
    private ResourceType menuType;

    @Builder
    public ManagerMenu(Long parentId, String menuLevel, String menuCode, String menuName, int ordering, String url, String uri, String menuDisplay, ResourceType menuType) {
        this.parentId = parentId;
        this.menuLevel = menuLevel;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.ordering = ordering;
        this.url = url;
        this.uri = uri;
        this.menuDisplay = menuDisplay;
        this.menuType = menuType;
    }

}
