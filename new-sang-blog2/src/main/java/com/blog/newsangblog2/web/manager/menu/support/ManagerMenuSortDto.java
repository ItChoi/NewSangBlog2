package com.blog.newsangblog2.web.manager.menu.support;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ManagerMenuSortDto {

    private List<Long> ids;
    private List<Integer> orderings;

}
