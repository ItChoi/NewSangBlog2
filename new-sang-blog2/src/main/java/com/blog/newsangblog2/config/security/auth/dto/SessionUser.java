package com.blog.newsangblog2.config.security.auth.dto;

import com.blog.newsangblog2.web.manager.user.domain.Manager;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(Manager manager) {
        this.name = manager.getName();
        this.email = manager.getEmail();
        this.picture = manager.getImageFileName();
    }
}
