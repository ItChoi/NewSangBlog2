package com.blog.newsangblog2.web.manager.portfolio.support;

import com.blog.newsangblog2.web.manager.user.support.ManagerDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioRequestDto {

    private ManagerDto manager;
    private String title;
    private String content;
    private String url;
    private String zipFile;
    private String displayFlag;
    private String forceDisplayFlag;
}
