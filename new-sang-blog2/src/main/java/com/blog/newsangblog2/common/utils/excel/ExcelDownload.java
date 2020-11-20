package com.blog.newsangblog2.common.utils.excel;


import com.blog.newsangblog2.common.utils.excel.enumeration.ExcelDownloadSampleDto;
import com.blog.newsangblog2.common.utils.excel.enumeration.ExcelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExcelDownload<T> {

    private static final SpreadsheetVersion supplyExcelVersion = SpreadsheetVersion.EXCEL2007;
    private static final int ROW_START_INDEX = 0;
    private static final int COLUMN_START_INDEX = 0;

    private XSSFWorkbook wb;
    private Sheet sheet;

    public XSSFWorkbook getWb() {
        return this.wb;
    }

    public ExcelDownload(List<String> head, List<T> body, ExcelType type) {
        excelValidator(body);
        this.wb = new XSSFWorkbook();
        sheet = this.wb.createSheet();
        int bodyRowStartIndex = ROW_START_INDEX + 1;
        renderHead(head);

        if (type.equals(ExcelType.ALL_DOWNLOAD)) {
            renderBody(body, bodyRowStartIndex);
        } else if (type.equals(ExcelType.SELECTED_DOWNLOAD)) {
            renderSelectedBody(body, head, bodyRowStartIndex);
        }


    }

    private void excelValidator(List<T> body) {
        if (body == null) {
            throw new NullPointerException("엑셀 데이터를 입력해주세요.");
        }

        int maxRows = supplyExcelVersion.getMaxRows();

        if (body.size() > maxRows) {
            throw new IllegalArgumentException(String.format("엑셀 업로드 시 %s개 이하만 넣어주세요.", maxRows));
        }
    }

    private void renderHead(List<String> head) {
        Row row = sheet.createRow(ROW_START_INDEX);

        int colIndex = COLUMN_START_INDEX;
        for (String field : head) {
            Cell cell = row.createCell(colIndex++);
            cell.setCellValue(field);
        }
    }

    private void renderBody(List<T> body, int rowIndex) {
        for (Object rowData : body) {
            Row row = sheet.createRow(rowIndex++);
            int colIndex = COLUMN_START_INDEX;

            for (Field field : rowData.getClass().getDeclaredFields()) {
                try {
                    // private 접근 제어자 접근 가능하도록 설정
                    field.setAccessible(true);
                    Cell cell = row.createCell(colIndex++);
                    ExcelUtils.renderCellValue(cell, field.get(body));
                } catch (Exception e) {
                    log.error("ERROR: {}", e);
                }

            }
        }
    }

    private void renderSelectedBody(List<T> body, List<String> head, int rowIndex) {
        Map headInfo = ExcelDownloadSampleDto.convertListToMap(head);

        for (Object rowData : body) {
            Row row = sheet.createRow(rowIndex++);
            int colIndex = COLUMN_START_INDEX;

            for (Field field : rowData.getClass().getDeclaredFields()) {
                if (headInfo.containsKey(field.getName())) {
                    try {
                        // private 접근 제어자 접근 가능하도록 설정
                        field.setAccessible(true);
                        Cell cell = row.createCell(colIndex++);
                        ExcelUtils.renderCellValue(cell, field.get(rowData));
                    } catch (Exception e) {
                        log.error("ERROR: {}", e);
                    }
                }
            }
        }
    }

}
