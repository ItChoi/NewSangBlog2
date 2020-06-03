package com.blog.newsangblog2.web.manager.portfolio;

import com.blog.newsangblog2.common.domain.ResponseWrapperDto;
import com.blog.newsangblog2.web.manager.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/manager/portfolio")
@Controller
public class ManagerPortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<ResponseWrapperDto> portfolioList(@RequestParam("portfolioId") Long id) {

        return null;
    }



}
