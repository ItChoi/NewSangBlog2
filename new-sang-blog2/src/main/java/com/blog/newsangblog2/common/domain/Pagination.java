package com.blog.newsangblog2.common.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class Pagination {
    private int number;              // 현재 페이지
    private int size;                // 페이지 크기
    private int totalPages;          // 전체 페이지 수
    private int numberOfElements;    // 현재 페이지에 나올 데이터 수
    private long totalElements;      // 전체 데이터 수
    private boolean hasPreviousPage; // 이전 페이지 존재 여부
    private boolean firstPage;       // 현재 페이지가 첫 페이지인지 여부 
    private boolean nextPage;            // 다음 페이지 여부
    private boolean lastPage;            // 현재 페이지가 마지막 페이지인지 여부
    private Sort sort;               // 정렬 정보

    @Builder
    public Pagination(int number, int size, int totalPages, long totalElements) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
