package com.blog.newsangblog2.manager.menu.service;

import com.blog.newsangblog2.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.manager.menu.domain.QManagerMenu;
import com.blog.newsangblog2.manager.menu.repository.ManagerMenuRepository;
import com.blog.newsangblog2.manager.menu.support.ManagerMenuDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ManagerMenuServiceImpl implements ManagerMenuService {

    private final ManagerMenuRepository managerMenuRepository;

}
