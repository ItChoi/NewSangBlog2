package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.domain.UserRole;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManagerUserServiceImplTest {

    @Autowired
    private ManagerUserRepository managerUserRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    @Rollback(false)
    @Test
    void createManager() {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setLoginId("loginid");
        managerDto.setPassword("password");
        managerDto.setPhoneNumber("0100000");
        managerDto.setName("최모씨");
        managerDto.setEmail("test@test.com");
        managerDto.setIntroduce("자기소개입니당");

        Manager manager = modelMapper.map(managerDto, Manager.class);

        manager.setUserRole(
                UserRole.builder()
                .authority(UserRoleType.ADMIN)
                .build()
        );

        managerUserRepository.save(manager);

        System.out.println("result1: " + manager.getId());
        System.out.println("result2: " + manager.getUserRole().getId());

    }

    @Test
    void checkDuplicationValue() {
    }
}