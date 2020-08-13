package com.blog.newsangblog2.web.manager.portfolio;

import com.blog.newsangblog2.common.support.CommonListDto;
import com.blog.newsangblog2.common.support.ResponseWrapperDto;
import com.blog.newsangblog2.web.manager.hashtag.support.HashTagDto;
import com.blog.newsangblog2.web.manager.portfolio.service.PortfolioService;
import com.blog.newsangblog2.web.manager.portfolio.support.PortfolioFormDto;
import com.blog.newsangblog2.web.manager.portfolio.support.PortfolioRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/manager/portfolio")
@Controller
public class ManagerPortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping(value = {"", "/"})
    public String portfolioList(CommonListDto commonListDto, Model model) {
        ResponseWrapperDto responseDto = portfolioService.getPortfolioList(commonListDto);
        model.addAttribute("listDto", responseDto);

        return "manager/portfolio/list";
    }

    @GetMapping("/my")
    public String myPortfolioList(CommonListDto commonListDto, Model model) {
        ResponseWrapperDto responseDto = portfolioService.getMyPortfolioList(commonListDto);
        model.addAttribute("listDto", responseDto);

        return "manager/portfolio/list";
    }


    @GetMapping("/create")
    public String createPortfolio(Model model) {
        model.addAttribute("portfolioInfo", new PortfolioFormDto());
        return "/manager/portfolio/form";
    }

    @PostMapping("/temp-save")
    public String tempSavePortfolio(@RequestBody PortfolioFormDto requestDto) {
        System.out.println("test");
        return "";
    }

    @PostMapping("/hashtag")
    public ResponseEntity<HashTagDto> hashtag(@RequestBody HashTagDto hashTagDto) {



        return null;
    }



    @PostMapping
    public ResponseEntity<Long> createPortfolio(PortfolioRequestDto portfolioRequestDto) {
        Long portfolioId = portfolioService.createPortfolio(portfolioRequestDto);

        return new ResponseEntity<>(portfolioId, HttpStatus.OK);
    }

    @PostMapping("/upload_image_test")
    public String test(MultipartFile file) {

        System.out.println("testasdadasd");

        return "";
    }

}
