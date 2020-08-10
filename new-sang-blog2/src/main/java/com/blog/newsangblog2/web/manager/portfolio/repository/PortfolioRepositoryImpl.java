package com.blog.newsangblog2.web.manager.portfolio.repository;

import com.blog.newsangblog2.common.support.CommonListDto;
import com.blog.newsangblog2.web.manager.portfolio.domain.Portfolio;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.blog.newsangblog2.web.manager.portfolio.domain.QPortfolio.portfolio;

@RequiredArgsConstructor
public class PortfolioRepositoryImpl implements PortfolioRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<Portfolio> findAll(CommonListDto commonListDto) {
        Pageable pageable = getPageable(commonListDto);

        QueryResults<Portfolio> result = queryFactory
                .select(portfolio)
                .from(portfolio)
                .where(
                        selectSearchEq(commonListDto.getWhere(), commonListDto.getQuery())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(portfolio.createdDate.asc())
                .fetchResults();


        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<Portfolio> findAllByLoginId(String loginId, CommonListDto commonListDto) {
        Pageable pageable = getPageable(commonListDto);

        QueryResults<Portfolio> result = queryFactory
                .select(portfolio)
                .from(portfolio)
                .where(
                        loginIdEq(loginId),
                        selectSearchEq(commonListDto.getWhere(), commonListDto.getQuery())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(portfolio.createdDate.asc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression loginIdEq(String loginId) {
        return loginId != null ? portfolio.manager.loginId.eq(loginId) : null;
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? portfolio.manager.id.eq(userId) : null;
    }


    private BooleanExpression selectSearchEq(String where, String query) {



        return null;
    }


    private Pageable getPageable(CommonListDto commonListDto) {
        return PageRequest.of(commonListDto.getPage(), commonListDto.getSize());
    }




}
