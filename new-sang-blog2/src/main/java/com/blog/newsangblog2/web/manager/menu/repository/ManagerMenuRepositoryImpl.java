package com.blog.newsangblog2.web.manager.menu.repository;

import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.blog.newsangblog2.web.manager.menu.domain.QManagerMenu.managerMenu;

@RequiredArgsConstructor
public class ManagerMenuRepositoryImpl implements ManagerMenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<ManagerMenu> findAllByFirstLevel() {
        return queryFactory
                .select(managerMenu)
                .from(managerMenu)
                .where(
                        menuLevelEq(1)
                )
                .orderBy(managerMenu.ordering.asc())
                .fetch();
    }

    private BooleanExpression menuLevelEq(Integer level) {
        return level == null ? null : managerMenu.menuLevel.eq(level);
    }





}
