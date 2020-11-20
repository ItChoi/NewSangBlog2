package com.blog.newsangblog2.common.utils.excel;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ExcelUtils {

    public static void renderCellValue(Cell cell, Object cellValue) {
        if (cellValueIsNumber(cellValue)) {
            Number numberValue = (Number) cellValue;
            cell.setCellValue(numberValue.doubleValue());
        } else {
            cell.setCellValue(cellValue == null ? "" : cellValue.toString());
        }
    }


    public static boolean cellValueIsNumber(Object cellValue) {
        if (cellValue instanceof Number) {
            return true;
        }

        return false;
    }

    @SuppressWarnings("serial")
    public static Map<Class<?>, PropertyEditor> defaultEditors = new HashMap<Class<?>, PropertyEditor>() {
        {
            put(boolean.class, new CustomBooleanEditor(false));
            put(Boolean.class, new CustomBooleanEditor(true));
            put(byte.class, new CustomNumberEditor(Byte.class, false));
            put(Byte.class, new CustomNumberEditor(Byte.class, true));
            put(int.class, new CustomNumberEditor(Integer.class, false));
            put(Integer.class, new CustomNumberEditor(Integer.class, true));
            put(long.class, new CustomNumberEditor(Long.class, false));
            put(Long.class, new CustomNumberEditor(Long.class, true));
            put(double.class, new CustomNumberEditor(Double.class, false));
            put(Double.class, new CustomNumberEditor(Double.class, true));
        }
    };
}
