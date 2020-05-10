package com.blog.newsangblog2.web.manager.menu.domain;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;
import com.blog.newsangblog2.common.enumeration.ResourceType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class ManagerMenu extends BaseDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_menu_parent_id")
    private ManagerMenu parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ManagerMenu> child = new ArrayList<>();


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
