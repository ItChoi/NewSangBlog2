package com.blog.newsangblog2.web.manager.menu.domain;

import com.blog.newsangblog2.common.domain.BaseDateTimeEntity;
import com.blog.newsangblog2.common.enumeration.ResourceType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@ToString(exclude = {"parent"})
@Getter @Setter
@NoArgsConstructor
@Entity
public class ManagerMenu extends BaseDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private ManagerMenu parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ManagerMenu> child = new ArrayList<>();

    @Column(length = 10)
    private Integer menuLevel;

    @Column(length = 100)
    private String menuCode;

    @Column(length = 100)
    private String menuName;

    @Column
    private Integer ordering;

    @Column(length = 100)
    private String url;

    @Column(length = 100)
    private String uri;

    @Column(length = 1)
    private String menuDisplay;

    @Enumerated(EnumType.STRING)
    private ResourceType menuType;



    /*@Builder
    public ManagerMenu(Integer menuLevel, String menuCode, String menuName, int ordering, String url, String uri, String menuDisplay, ResourceType menuType) {
        this.menuLevel = menuLevel;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.ordering = ordering;
        this.url = url;
        this.uri = uri;
        this.menuDisplay = menuDisplay;
        this.menuType = menuType;
    }*/

}
