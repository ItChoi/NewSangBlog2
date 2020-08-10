package com.blog.newsangblog2.web.manager.portfolio.repository;

import com.blog.newsangblog2.common.support.CommonListDto;
import com.blog.newsangblog2.web.manager.portfolio.domain.Portfolio;
import org.springframework.data.domain.Page;

public interface PortfolioRepositoryCustom {
    Page<Portfolio> findAll(CommonListDto commonListDto);

    Page<Portfolio> findAllByLoginId(String loginId, CommonListDto commonListDto);
}
