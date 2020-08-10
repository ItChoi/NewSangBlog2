package com.blog.newsangblog2.web.manager.portfolio.support;

import com.blog.newsangblog2.common.enumeration.CommonImageRepositoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PortfolioFormDto {
    private Long id;
    private Long managerId;
    private String title;
    private String content;
    private String url;
    private String zipFile;
    private String displayFlag;
    private String forceDisplayFlag;
    private List<CommonImageRepositoryDto> commonImageRepositoryList;
}
