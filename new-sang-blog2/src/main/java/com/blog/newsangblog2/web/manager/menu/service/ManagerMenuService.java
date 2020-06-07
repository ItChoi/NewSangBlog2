package com.blog.newsangblog2.web.manager.menu.service;

import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuDto;

import java.util.List;

public interface ManagerMenuService {


    List<ManagerMenuDto> getManagerMenuList();

    ManagerMenuDto getFindById(Long id);
}
