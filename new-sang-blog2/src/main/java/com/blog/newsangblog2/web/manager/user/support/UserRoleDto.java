package com.blog.newsangblog2.web.manager.user.support;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDto {
    private Long id;
    private ManagerDto manager;
    private Long managerId;
    private UserRoleType authority;
}
