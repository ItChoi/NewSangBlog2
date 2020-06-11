package com.blog.newsangblog2.web.manager.menu;

import com.blog.newsangblog2.common.enumeration.FileType;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
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
import org.springframework.web.bind.annotation.*;

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
    public String editMenu(@RequestParam(required = false, value = "menuId") Long menuId, Model model) {
        List<ManagerMenuDto> responseDtos = managerMenuService.sortMenuListOrdering();
        List<String> fileTypes = FileUtils.getResourceByType(FileType.FILE.getType());

        if (menuId != null) {
            model.addAttribute("existsMenuId", menuId);
        }
        model.addAttribute("managerMenuList", responseDtos);
        model.addAttribute("menuTypes", fileTypes);

        return "/manager/menu/form";
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<ManagerMenuDto> menuDetail(@PathVariable("menuId") Long id) {
        ManagerMenuDto dto = managerMenuService.getFindById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public String menuSave(ManagerMenuDto managerMenuDto) {
        Long id = managerMenuService.saveMenu(managerMenuDto);

        return "redirect:/manager/menu/edit?menuId=" + id;
    }


}
