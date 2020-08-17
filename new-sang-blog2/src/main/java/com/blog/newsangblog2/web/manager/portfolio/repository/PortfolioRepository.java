package com.blog.newsangblog2.web.manager.portfolio.repository;

import com.blog.newsangblog2.web.manager.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>, PortfolioRepositoryCustom {
    @Query("select max(p.ordering) from Portfolio p left join p.manager m where m.id = :id")
    Integer getMaxOrderingByManagerId(@Param("id") Long id);
}
