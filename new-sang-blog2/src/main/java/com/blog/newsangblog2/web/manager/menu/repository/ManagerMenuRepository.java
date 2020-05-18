package com.blog.newsangblog2.web.manager.menu.repository;

import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerMenuRepository extends JpaRepository<ManagerMenu, Long>, ManagerMenuRepositoryCustom {
    List<ManagerMenu> findByParentIdAndMenuLevel(Long parentId, String menuLevel);
}
