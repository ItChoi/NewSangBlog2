package com.blog.newsangblog2.web.manager.menu.support;

import com.blog.newsangblog2.common.enumeration.ResourceType;
import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ManagerMenuDto extends ManagerMenuSearch {

    private Long id;
    private Long parentId;

    // private ManagerMenuDto parent; // 부모 - 자식 호출이 연달아 되어 무한루프에 빠짐. parentId로 해결 하기...
    private List<ManagerMenuDto> child;
    private Integer menuLevel;
    private String menuCode;
    private String menuName;
    private Integer ordering;
    private String url;
    private String uri;
    private String menuDisplay;
    private ResourceType menuType;

    /*public ManagerMenu toEntity() {
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
    }*/

    public ManagerMenuDto sortMenuOrdering() {
        if (this.child != null && this.child.size() != 0) {
            this.child = this.child.stream()
                    .sorted(
                            (o1, o2) -> o1.getOrdering().compareTo(o2.getOrdering())
                    )
                    .collect(Collectors.toList());
        }

        return this;
    }
}
