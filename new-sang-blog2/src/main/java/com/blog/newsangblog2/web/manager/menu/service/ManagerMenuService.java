package com.blog.newsangblog2.web.manager.menu.service;

import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuDto;
import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuSortDto;

import java.util.List;

public interface ManagerMenuService {

    List<ManagerMenuDto> getManagerMenuList();

    ManagerMenuDto getFindById(Long id);

    Long saveMenu(ManagerMenuDto managerMenuDto);

    List<ManagerMenuDto> sortMenuListOrdering();

    void changeMenuSort(ManagerMenuSortDto managerMenuSortDto);

}
