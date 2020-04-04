package com.blog.newsangblog2.manager.menu.repository;

import com.blog.newsangblog2.manager.menu.domain.ManagerMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerMenuRepository extends JpaRepository<ManagerMenu, Long> {
    public List<ManagerMenu> findByParentIdAndMenuLevel(Long parentId, String menuLevel);
}
