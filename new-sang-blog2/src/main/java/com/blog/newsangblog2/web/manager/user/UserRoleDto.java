package com.blog.newsangblog2.web.manager.user;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDto {
    private Long id;
    private ManagerDto manager;
    private UserRoleType authority;
}
