package com.blog.newsangblog2.web.manager.menu.repository;

import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;

import java.util.List;
import java.util.Optional;

public interface ManagerMenuRepositoryCustom {
    List<ManagerMenu> findAllByFirstLevel();

}
