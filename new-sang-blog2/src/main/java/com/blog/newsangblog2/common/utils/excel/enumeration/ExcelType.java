package com.blog.newsangblog2.common.utils.excel.enumeration;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum ExcelType {
    SELECTED_DOWNLOAD("선택 다운로드", "selected"),
    ALL_DOWNLOAD("전체 다운로드", "all");

    private final String text;
    private final String type;
}
