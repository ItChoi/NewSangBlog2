package com.blog.newsangblog2.web.manager.menu.service;

import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ManagerMenuServiceImpl implements ManagerMenuService {

    private final ManagerMenuRepository managerMenuRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<ManagerMenuDto> getManagerMenuList() {
        List<ManagerMenu> managerMenuList = managerMenuRepository.findAllByFirstLevel();
        return managerMenuList.stream()
                .map(menu -> modelMapper.map(menu, ManagerMenuDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ManagerMenuDto getFindById(Long id) {
        ManagerMenu findManagerMenu = managerMenuRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        return modelMapper.map(findManagerMenu, ManagerMenuDto.class);
    }

    @Transactional
    @Override
    public Long saveMenu(ManagerMenuDto inputMenuDto) {
        Long id = inputMenuDto.getId();

        if (id == null) {
            ManagerMenu inputManagerMenu = modelMapper.map(inputMenuDto, ManagerMenu.class);
            inputManagerMenu.setMenuLevel(1);

            Integer orderingIndex = managerMenuRepository.getMaxOrderingByParentIdIsNull();
            orderingIndex = orderingIndex == null ? 0 : orderingIndex;
            inputManagerMenu.setOrdering(orderingIndex + 1);

            managerMenuRepository.save(inputManagerMenu);

            id = inputManagerMenu.getId();
        } else {
            ManagerMenu findManagerMenu = managerMenuRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(inputMenuDto.getId().toString()));

            findManagerMenu.setMenuCode(inputMenuDto.getMenuCode());
            findManagerMenu.setMenuName(inputMenuDto.getMenuName());
            findManagerMenu.setMenuType(inputMenuDto.getMenuType());
            findManagerMenu.setUrl(inputMenuDto.getUrl());
            findManagerMenu.setUri(inputMenuDto.getUri());
            findManagerMenu.setMenuDisplay(inputMenuDto.getMenuDisplay());
        }

        return id;
    }



}
