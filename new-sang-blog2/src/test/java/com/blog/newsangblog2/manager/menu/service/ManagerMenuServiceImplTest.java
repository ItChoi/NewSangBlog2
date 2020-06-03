package com.blog.newsangblog2.manager.menu.service;

import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import com.blog.newsangblog2.web.manager.menu.service.ManagerMenuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class ManagerMenuServiceImplTest {


    @Test
    public void 테스트() {

        String test = "01011112222";

        System.out.println(test.length());
        System.out.println(test.substring(0, 3));
        System.out.println(test.substring(3, 7));
        System.out.println(test.substring(7, 11));




    }

}