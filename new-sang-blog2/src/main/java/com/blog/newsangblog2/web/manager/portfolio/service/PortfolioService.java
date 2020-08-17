package com.blog.newsangblog2.web.manager.portfolio.service;

import com.blog.newsangblog2.common.support.CommonListDto;
import com.blog.newsangblog2.common.support.ResponseWrapperDto;
import com.blog.newsangblog2.web.manager.portfolio.support.PortfolioRequestDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PortfolioService {
    Long createPortfolio(PortfolioRequestDto portfolioRequestDto);

    ResponseWrapperDto getPortfolioList(CommonListDto commonListDto);

    ResponseWrapperDto getMyPortfolioList(CommonListDto commonListDto);

}
