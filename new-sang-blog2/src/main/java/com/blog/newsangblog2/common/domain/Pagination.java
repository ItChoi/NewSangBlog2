package com.blog.newsangblog2.common.domain;


import com.blog.newsangblog2.web.manager.portfolio.domain.Portfolio;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class Pagination {
    private int number;              // 현재 페이지
    private Integer size;            // 페이지 크기
    private int totalPages;          // 전체 페이지 수
    private int numberOfElements;    // 현재 페이지에 나올 데이터 수
    private long totalElements;      // 전체 데이터 수
    private boolean hasPreviousPage; // 이전 페이지 존재 여부
    private boolean firstPage;       // 현재 페이지가 첫 페이지인지 여부 
    private boolean nextPage;        // 다음 페이지 여부
    private boolean lastPage;        // 현재 페이지가 마지막 페이지인지 여부
    private Sort sort;               // 정렬 정보

    private int startPage;
    private int endPage;

    @Builder
    public Pagination(int number, int size, int totalPages, int numberOfElements, long totalElements, boolean hasPreviousPage, boolean nextPage, boolean firstPage, boolean lastPage, Sort sort) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
        this.totalElements = totalElements;
        this.hasPreviousPage = hasPreviousPage;
        this.nextPage = nextPage;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.sort = sort;
        this.startPage = (number / 10) * 10 + 1;
        this.endPage = this.startPage + this.size - 1;
        this.endPage = this.endPage > this.totalPages ? this.totalPages : this.endPage;

    }

    public static Pagination initPagination(Page<Portfolio> findPortfolioList) {
        return Pagination.builder()
                .number(findPortfolioList.getNumber())
                .size(findPortfolioList.getSize())
                .totalPages(findPortfolioList.getTotalPages())
                .numberOfElements(findPortfolioList.getNumberOfElements())
                .totalElements(findPortfolioList.getTotalElements())
                .hasPreviousPage(findPortfolioList.getPageable().hasPrevious())
                .nextPage(findPortfolioList.hasNext())
                .firstPage(findPortfolioList.isFirst())
                .lastPage(findPortfolioList.isLast())
                .sort(findPortfolioList.getSort())
                .build();
    }
}
