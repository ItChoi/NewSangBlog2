package com.blog.newsangblog2.common.utils.excel;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;

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

    public ExcelDownload(List<String> head, List<T> body) {
        excelValidator(body);
        this.wb = new XSSFWorkbook();
        sheet = this.wb.createSheet();
        renderHead(head);
        renderBody(body);
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

    private void renderBody(List<T> body) {
        int rowIndex = ROW_START_INDEX + 1;
        int colIndex = COLUMN_START_INDEX;

        for (Object rowData : body) {
            Row row = sheet.createRow(rowIndex++);

            for (Field field : rowData.getClass().getDeclaredFields()) {
                // private 접근 제어자 접근 가능하도록 설정
                try {
                    field.setAccessible(true);
                    Cell cell = row.createCell(colIndex++);
                    renderCellValue(cell, field.get(rowData));
                } catch (Exception e) {
                    log.error("ERROR: {}", e);
                }
            }
        }
    }

    private void renderCellValue(Cell cell, Object cellValue) {
        if (cellValue instanceof Number) {
            Number numberValue = (Number) cellValue;
            cell.setCellValue(numberValue.doubleValue());
            return;
        }

        cell.setCellValue(cellValue == null ? "" : cellValue.toString());
    }

}
