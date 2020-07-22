package com.blog.newsangblog2.web.manager.portfolio.repository;

import com.blog.newsangblog2.web.manager.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>, PortfolioRepositoryCustom {

}
