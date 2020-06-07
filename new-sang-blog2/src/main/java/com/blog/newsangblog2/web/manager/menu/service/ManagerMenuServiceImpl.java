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
}
