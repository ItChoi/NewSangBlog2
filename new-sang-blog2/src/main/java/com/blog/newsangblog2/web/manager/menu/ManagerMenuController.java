package com.blog.newsangblog2.web.manager.menu;

import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import com.blog.newsangblog2.web.manager.menu.service.ManagerMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/manager/menu")
@Controller
public class ManagerMenuController {

    private final ManagerMenuService managerMenuService;

    private final ManagerMenuRepository managerMenuRepository;

    @GetMapping(value = {"/", ""})
    public List<ManagerMenu> managerMenuList() {

        List<ManagerMenu> allByFirstLevel = managerMenuRepository.findAllByFirstLevel();
        System.out.println("test: ");

        return null;
    }

}
