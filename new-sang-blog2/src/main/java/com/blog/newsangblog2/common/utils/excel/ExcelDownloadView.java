package com.blog.newsangblog2.common.utils.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("excelDownload")
public class ExcelDownloadView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) throws Exception {
        XSSFWorkbook workbook = (XSSFWorkbook) model.get("workbook");

        ServletOutputStream sos = res.getOutputStream();
        workbook.write(sos);
    }

}
