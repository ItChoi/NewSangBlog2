package com.blog.newsangblog2.web.manager.menu.repository;

import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerMenuRepository extends JpaRepository<ManagerMenu, Long>, ManagerMenuRepositoryCustom {
    List<ManagerMenu> findByParentIdAndMenuLevel(Long parentId, String menuLevel);

    @Query("select max(mm.ordering) from ManagerMenu mm where mm.parent.id = :parentId")
    Integer getMaxOrderingByParentId(@Param("parentId") Long parentId);

    @Query("select max(mm.ordering) from ManagerMenu mm where mm.parent.id is null")
    Integer getMaxOrderingByParentIdIsNull();



}
