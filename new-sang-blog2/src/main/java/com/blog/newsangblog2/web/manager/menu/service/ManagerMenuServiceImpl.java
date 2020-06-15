package com.blog.newsangblog2.web.manager.menu.service;

import com.blog.newsangblog2.common.exception.DataNotFoundException;
import com.blog.newsangblog2.common.exception.DuplicationException;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuDto;
import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuSortDto;
import com.sun.media.sound.InvalidDataException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public List<ManagerMenuDto> sortMenuListOrdering() {
        List<ManagerMenuDto> managerMenuList = getManagerMenuList();
        sortMenu(managerMenuList);

        return managerMenuList;
    }

    @Override
    public ManagerMenuDto getFindById(Long id) {
        ManagerMenu findManagerMenu = managerMenuRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
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


            ManagerMenu parent = managerMenuRepository.findById(inputMenuDto.getParentId())
                    .orElseThrow(() -> new UserNotFoundException(inputMenuDto.getParentId().toString()));
            findManagerMenu.setParent(parent);

            findManagerMenu.setMenuCode(inputMenuDto.getMenuCode());
            findManagerMenu.setMenuName(inputMenuDto.getMenuName());
            findManagerMenu.setMenuType(inputMenuDto.getMenuType());
            findManagerMenu.setUrl(inputMenuDto.getUrl());
            findManagerMenu.setUri(inputMenuDto.getUri());
            findManagerMenu.setMenuDisplay(inputMenuDto.getMenuDisplay());
        }

        return id;
    }

    private void setDtoToEntity(ManagerMenuDto dto, ManagerMenu entity) {

    }

    @Transactional
    @Override
    public void changeMenuSort(ManagerMenuSortDto dto) {
        for (int i = 0; i < dto.getIds().size(); i++) {
            Long id = dto.getIds().get(i);
            Integer orderIndex = dto.getOrderings().get(i);

            Optional.of(orderIndex).orElseThrow(() -> new NullPointerException());

            ManagerMenu findMenu = managerMenuRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
            findMenu.setOrdering(orderIndex);
        }
    }

    private void sortMenu(List<ManagerMenuDto> managerMenuList) {
        managerMenuList
                .forEach(level2 -> {
                    level2 = level2.sortMenuOrdering();

                    if (level2.getChild() != null) {
                        level2.getChild()
                                .forEach(level3 -> {
                                    level3 = level3.sortMenuOrdering();

                                    if (level3.getChild() != null) {
                                        level3.getChild()
                                                .forEach(level4 -> {
                                                    level4 = level4.sortMenuOrdering();
                                                });
                                    }

                                });
                    }
                });
    }

    private void changeLevelOrOrdering() {

    }

    private void sortOrdering() {

    }



}
