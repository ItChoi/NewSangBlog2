package com.blog.newsangblog2.web.manager.menu.service;

import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ManagerMenuServiceImpl implements ManagerMenuService {

    private final ManagerMenuRepository managerMenuRepository;

}
