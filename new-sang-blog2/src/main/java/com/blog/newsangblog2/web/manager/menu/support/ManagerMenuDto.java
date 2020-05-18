package com.blog.newsangblog2.web.manager.menu.support;

import com.blog.newsangblog2.common.enumeration.ResourceType;
import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ManagerMenuDto extends ManagerMenuSearch {

    private Long parentId;
    private Integer menuLevel;
    private String menuCode;
    private String menuName;
    private Integer ordering;
    private String url;
    private String uri;
    private String menuDisplay;
    private ResourceType menuType;

    public ManagerMenu toEntity() {
        return ManagerMenu.builder()
                .menuLevel(menuLevel)
                .menuCode(menuCode)
                .menuName(menuName)
                .ordering(ordering)
                .url(url)
                .uri(uri)
                .menuDisplay(menuDisplay)
                .menuType(menuType)
                .build();
    }
}
