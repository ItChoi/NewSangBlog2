package com.blog.newsangblog2.manager.menu.service;

import com.blog.newsangblog2.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.manager.menu.repository.ManagerMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ManagerMenuServiceImpl implements ManagerMenuService {

    private final ManagerMenuRepository managerMenuRepository;

    @Override
    public List<ManagerMenu> customFindAll() {



        return null;
    }

}
