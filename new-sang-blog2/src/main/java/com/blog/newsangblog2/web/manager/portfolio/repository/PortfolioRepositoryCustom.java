package com.blog.newsangblog2.web.manager.portfolio.repository;

import com.blog.newsangblog2.common.domain.CommonListDto;
import com.blog.newsangblog2.web.manager.portfolio.domain.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PortfolioRepositoryCustom {
    Page<Portfolio> findAll(CommonListDto commonListDto);

    Page<Portfolio> findAllByUserId(Long userId, CommonListDto commonListDto);
}
