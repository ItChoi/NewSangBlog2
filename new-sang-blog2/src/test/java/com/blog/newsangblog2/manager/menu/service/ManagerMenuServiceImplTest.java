package com.blog.newsangblog2.manager.menu.service;

import com.blog.newsangblog2.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.manager.menu.repository.ManagerMenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ManagerMenuServiceImplTest {

    @Autowired
    private ManagerMenuService managerMenuService;

    @Autowired
    private ManagerMenuRepository managerMenuRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Test
    public void 테스트() {

        // List<ManagerMenu> managerMenus = managerMenuRepository.findAll();
        List<ManagerMenu> findManagerMenu = managerMenuRepository.findByParentIdAndMenuLevel(null, "1");

        for (ManagerMenu managerMenu : findManagerMenu) {
            System.out.println("testParent: " + managerMenu.getParent());
            System.out.println("1차 메뉴: " + managerMenu.getMenuCode());
            System.out.println("testChild: " + managerMenu.getChild().size());
        }



    }

}