package com.blog.newsangblog2.web.manager.portfolio.repository;

import com.blog.newsangblog2.common.domain.CommonListDto;
import com.blog.newsangblog2.web.manager.portfolio.domain.Portfolio;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
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

        QueryResults<Portfolio> result = queryFactory
                .select(portfolio)
                .from(portfolio)
                .where(
                        selectSearchEq(commonListDto.getWhere(), commonListDto.getQuery())
                )
                .orderBy(portfolio.createdDate.asc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), getPageable(commonListDto), result.getTotal());
    }

    @Override
    public Page<Portfolio> findAllByUserId(Long userId, CommonListDto commonListDto) {
        QueryResults<Portfolio> result = queryFactory
                .select(portfolio)
                .from(portfolio)
                .where(
                        userIdEq(userId),
                        selectSearchEq(commonListDto.getWhere(), commonListDto.getQuery())
                )
                .orderBy(portfolio.createdDate.asc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), getPageable(commonListDto), result.getTotal());
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? portfolio.manager.id.eq(userId) : null;
    }


    private BooleanExpression selectSearchEq(String where, String query) {



        return null;
    }


    private Pageable getPageable(CommonListDto commonListDto) {
        return PageRequest.of(commonListDto.getCurrentPage(), commonListDto.getItemPerPage());
    }


}
