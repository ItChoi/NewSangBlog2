package com.blog.newsangblog2.web.manager.portfolio.service;

import com.blog.newsangblog2.common.domain.CommonListDto;
import com.blog.newsangblog2.common.domain.Pagination;
import com.blog.newsangblog2.common.domain.ResponseWrapperDto;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.common.utils.UserUtils;
import com.blog.newsangblog2.web.manager.portfolio.domain.Portfolio;
import com.blog.newsangblog2.web.manager.portfolio.repository.PortfolioRepository;
import com.blog.newsangblog2.web.manager.portfolio.support.PortfolioRequestDto;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    private final ModelMapper modelMapper;

    private final ManagerUserRepository managerUserRepository;

    @Override
    public Long createPortfolio(PortfolioRequestDto portfolioRequestDto) {
        Portfolio portfolio = modelMapper.map(portfolioRequestDto, Portfolio.class);
        String currentLoginId = UserUtils.getLoginId();
        Manager manager = managerUserRepository.findManagerByLoginId(currentLoginId).orElseThrow(() -> new UserNotFoundException(currentLoginId));
        portfolio.setManager(manager);

        portfolioRepository.save(portfolio);

        return portfolio.getId();
    }

    @Override
    public ResponseWrapperDto getPortfolioList(CommonListDto commonListDto) {
        Page<Portfolio> findPortfolioList = portfolioRepository.findAll(commonListDto);

        return ResponseWrapperDto.builder()
                .list(findPortfolioList.getContent())
                .pagination(
                        Pagination.initPagination(findPortfolioList)
                )
                .build();
    }

    @Override
    public ResponseWrapperDto getMyPortfolioList(CommonListDto commonListDto, Long userId) {

        if (!managerUserRepository.existsById(userId)) {
            throw new UserNotFoundException(userId + "이 존재하지 않습니다.");
        }

        Page<Portfolio> findPortfolioList = portfolioRepository.findAllByUserId(userId, commonListDto);

        return ResponseWrapperDto.builder()
                .list(findPortfolioList.getContent())
                .pagination(
                        Pagination.initPagination(findPortfolioList)
                )
                .build();
    }
}
