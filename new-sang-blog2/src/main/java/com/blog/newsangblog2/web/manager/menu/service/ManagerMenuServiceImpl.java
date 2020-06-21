package com.blog.newsangblog2.web.manager.menu.service;

import com.blog.newsangblog2.common.exception.DuplicationException;
import com.blog.newsangblog2.common.exception.InvalidDataException;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuDto;
import com.blog.newsangblog2.web.manager.menu.support.ManagerMenuSortDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        ManagerMenu parent = null;
        if (inputMenuDto.getParentId() != null) {
            parent = managerMenuRepository.findById(inputMenuDto.getParentId()).orElse(null);
        }

        Integer menuLevel = parent != null ? parent.getMenuLevel() + 1 : 1;
        inputMenuDto.setMenuLevel(menuLevel);

        if (id == null) {
            id = createMenu(inputMenuDto, parent);
        } else {

            editMenu(inputMenuDto, parent);
        }

        return id;
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
    
    // 1레벨은 정렬되어서 가져오고, 2레벨 이후에서는 자바를 통해 순서 정렬
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



    private List<ManagerMenu> sortTargetMenu(ManagerMenu managerMenu) {
        List<ManagerMenu> findMenuList = null;
        if (managerMenu.getParent() != null) {
            findMenuList = managerMenuRepository.findByParentIdOrderByOrderingAsc(managerMenu.getParent().getId());
        } else {
            findMenuList = managerMenuRepository.findByParentIdIsNullOrderByOrderingAsc();
        }

        return findMenuList;
    }

    // ordering 기준 index asc
    private void basicSortOrder(List<ManagerMenu> managerMenuList) {
        int index = 1;

        for (int i = 0; i < managerMenuList.size(); i++) {
            managerMenuList.get(i).setOrdering(index++);
        }
    }

    private Long createMenu(ManagerMenuDto inputMenuDto, ManagerMenu parent) {
        ManagerMenu inputManagerMenu = modelMapper.map(inputMenuDto, ManagerMenu.class);
        inputManagerMenu.setOrdering(getMaxOrderingByParentId(inputMenuDto.getParentId()) + 1);
        inputManagerMenu.setParent(parent);
        managerMenuRepository.save(inputManagerMenu);

        return inputManagerMenu.getId();
    }


    private void changeParentData(ManagerMenu findManagerMenu, ManagerMenuDto inputMenuDto, ManagerMenu inputParent) {
            if (checkParentNullToNotNull(findManagerMenu, inputParent)) {
                changeChildMenuLevelUp(findManagerMenu);
            } else if (checkParentNotNullToNull(findManagerMenu, inputParent)) {
                changeChildMenuLevelDown(findManagerMenu);
            }

            List<ManagerMenu> originLevelMenuList = sortTargetMenu(findManagerMenu);
            originLevelMenuList.remove(findManagerMenu.getOrdering() - 1);
            basicSortOrder(originLevelMenuList);

            findManagerMenu.setOrdering(getMaxOrderingByParentId(inputMenuDto.getParentId()) + 1);
            findManagerMenu.setParent(inputParent);
    }

    private Long editMenu(ManagerMenuDto inputMenuDto, ManagerMenu inputParent) {
        ManagerMenu findManagerMenu = managerMenuRepository.findById(inputMenuDto.getId())
                .orElseThrow(() -> new UserNotFoundException(inputMenuDto.getId().toString()));

        if (checkChangeParent(findManagerMenu, inputParent)) {
            changeParentData(findManagerMenu, inputMenuDto, inputParent);
        }

        findManagerMenu.setMenuLevel(inputMenuDto.getMenuLevel());
        findManagerMenu.setMenuCode(inputMenuDto.getMenuCode());
        findManagerMenu.setMenuName(inputMenuDto.getMenuName());
        findManagerMenu.setMenuType(inputMenuDto.getMenuType());
        findManagerMenu.setUrl(inputMenuDto.getUrl());
        findManagerMenu.setUri(inputMenuDto.getUri());
        findManagerMenu.setMenuDisplay(inputMenuDto.getMenuDisplay());

        return findManagerMenu.getId();
    }

    private Integer getMaxOrderingByParentId(Long parentId) {
        Integer orderingIndex = null;

        if (parentId == null) {
            orderingIndex = managerMenuRepository.getMaxOrderingByParentIdIsNull();
        } else {
            orderingIndex = managerMenuRepository.getMaxOrderingByParentId(parentId);
        }

        orderingIndex = orderingIndex == null ? 0 : orderingIndex;

        return orderingIndex;
    }

    private boolean checkChangeParent(ManagerMenu findManagerMenu, ManagerMenu inputParent) {
        boolean isChange = false;

        if (checkParentNullToNotNull(findManagerMenu, inputParent)
                || checkParentNotNullToNull(findManagerMenu, inputParent)
                || checkNotNullParent(findManagerMenu, inputParent)) {


            isChange = true;
        }

        return isChange;
    }

    private boolean checkParentNullToNotNull(ManagerMenu findManagerMenu, ManagerMenu inputParent) {
        boolean isChange = false;
        if (findManagerMenu.getParent() == null && inputParent != null) {

            if (findManagerMenu.getId() == inputParent.getId()) {
                throw new InvalidDataException("자신을 부모 메뉴로 선택 할 수 없습니다.");
            }

            isChange = true;
        }

        return isChange;
    }

    private boolean checkParentNotNullToNull(ManagerMenu findManagerMenu, ManagerMenu inputParent) {
        boolean isChange = false;
        if (findManagerMenu.getParent() != null && inputParent == null) {

            isChange = true;
        }

        return isChange;
    }

    private boolean checkNotNullParent(ManagerMenu findManagerMenu, ManagerMenu inputParent) {
        boolean isChange = false;
        if (findManagerMenu.getParent() != null && inputParent != null && findManagerMenu.getParent().getId() != inputParent.getId()) {

            isChange = true;
        }

        return isChange;
    }



    private void changeChildMenuLevelUp(ManagerMenu managerMenu) {
        changeChildMenuLevelByIndex(managerMenu, 1);
    }


    private void changeChildMenuLevelDown(ManagerMenu managerMenu) {
        changeChildMenuLevelByIndex(managerMenu, -1);
    }

    private void changeChildMenuLevelByIndex(ManagerMenu managerMenu, int index) {
        managerMenu.setMenuLevel(managerMenu.getMenuLevel() + 1);
        managerMenu.getChild()
                .forEach(child2 -> {
                    child2.setMenuLevel(child2.getMenuLevel() + index);

                    child2.getChild()
                            .forEach(child3 -> {
                                child3.setMenuLevel(child3.getMenuLevel() + index);

                                child3.getChild()
                                        .forEach(child4 -> {
                                            child4.setMenuLevel(child4.getMenuLevel() + index);

                                            /*if (child4.getChild() != null && child4.getChild().size() != 0) {
                                                throw new Exception();
                                            }*/

                                        });

                            });
                });

    }


}

