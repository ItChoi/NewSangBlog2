package com.blog.newsangblog2.web.manager.menu;

import com.blog.newsangblog2.common.enumeration.FileType;
import com.blog.newsangblog2.common.utils.FileUtils;
import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import com.blog.newsangblog2.web.manager.menu.service.ManagerMenuService;
import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/manager/menu")
@Controller
public class ManagerMenuController {

    private final ManagerMenuService managerMenuService;

    private final ManagerMenuRepository managerMenuRepository;

    private final ModelMapper modelMapper;

    @GetMapping(value = {"/", ""})
    public List<ManagerMenu> managerMenuList() {
        List<ManagerMenu> allByFirstLevel = managerMenuRepository.findAllByFirstLevel();
        System.out.println("test: ");

        return null;
    }

    @GetMapping("/edit")
    public String editMenu(Model model) {
        List<ManagerMenuDto> responseDtos = managerMenuService.getManagerMenuList();
        model.addAttribute("managerMenuList", responseDtos);

        // List<String> fileTypes = Arrays.stream(ResourceType.values());
        List<String> fileTypes = FileUtils.getResourceByType(FileType.FILE.getType());

        model.addAttribute("menuTypes", fileTypes);

        return "/manager/menu/form";
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<ManagerMenuDto> menuDetail(@PathVariable("menuId") Long id) {
        ManagerMenuDto dto = managerMenuService.getFindById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
