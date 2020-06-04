package com.blog.newsangblog2.web.manager.portfolio.service;

import com.blog.newsangblog2.common.domain.CommonListDto;
import com.blog.newsangblog2.common.domain.ResponseWrapperDto;
import com.blog.newsangblog2.web.manager.portfolio.support.PortfolioRequestDto;

public interface PortfolioService {
    Long createPortfolio(PortfolioRequestDto portfolioRequestDto);

    ResponseWrapperDto getPortfolioList(CommonListDto commonListDto);

    ResponseWrapperDto getMyPortfolioList(CommonListDto commonListDto, Long id);
}
