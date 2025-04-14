package com.example.basicproject.utils;


import com.alibaba.fastjson2.JSONObject;
import com.example.basicproject.dto.SheetDto;
import com.example.basicproject.dto.SheetDataDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    public static Workbook generateBook(SheetDto sheetDto, Workbook workbook) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        if (workbook == null) {
            workbook = new XSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet(sheetDto.getSheetName());
        int index = 0;
        Row header = sheet.createRow(index++);
        List<String> sheetCellHeadName = sheetDto.getSheetCellHeadName();
        for (int i = 0; i < sheetCellHeadName.size(); i++) {
            header.createCell(i).setCellValue(sheetCellHeadName.get(i));
        }

        List<JSONObject> sheetCellData = sheetDto.getSheetCellData();
        Map<String, String> sheetCellHeadNameKey = sheetDto.getSheetCellHeadNameKey();
        for (JSONObject sheetCellDatum : sheetCellData) {
            Row row = sheet.createRow(index++);
            for (int i = 0; i < sheetCellHeadName.size(); i++) {
                String fieldKey = sheetCellHeadNameKey.get(sheetCellHeadName.get(i));
                if (sheetCellDatum.containsKey(fieldKey)) {
                    Object value = sheetCellDatum.get(fieldKey);
                    if (value instanceof Date) {
                        row.createCell(i).setCellValue(simpleDateFormat.format(value));
                    } else {
                        row.createCell(i).setCellValue(sheetCellDatum.get(fieldKey).toString());
                    }
                } else {
                    row.createCell(i).setCellValue(StringUtils.EMPTY);
                }
            }
        }

        return workbook;
    }

    public static Workbook generateBook(List<SheetDto> sheetDto) {
        Workbook workbook = new XSSFWorkbook();
        for (SheetDto sh : sheetDto) {
            generateBook(sh, workbook);
        }
        return workbook;
    }

    public static List<String> readCell(Row row) {
        List<String> res = new ArrayList<>();
        for (Cell cell : row) {
            res.add(cell.toString());
        }
        return res;
    }

    public static List<List<String>> readSheet(Sheet sheet, Integer readStartIndex) {
        List<List<String>> res = new ArrayList<>();
        Integer index = 0;
        if (readStartIndex == null) {
            readStartIndex = 0;
        }

        for (Row row : sheet) {
            if (index >= readStartIndex) {
                res.add(readCell(row));
            }
            index++;
        }
        return res;
    }

    public static XSSFWorkbook getAllSheets(InputStream inputStream) throws IOException {

        return new XSSFWorkbook(inputStream);
    }

    public static List<SheetDataDto> readExcel(InputStream inputStream, Integer readStartIndex) throws IOException {
        List<SheetDataDto> res = new ArrayList<>();
        XSSFWorkbook sheets = getAllSheets(inputStream);
        try {
            for (Sheet sheet : sheets) {
                res.add(new SheetDataDto(sheet.getSheetName(), readSheet(sheet, readStartIndex)));
            }
        } finally {
            sheets.close();
            inputStream.close();
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("示例数据");

        // 创建标题行
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("姓名");
        header.createCell(1).setCellValue("年龄");

        // 填充数据
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("张三");
        row1.createCell(1).setCellValue(28);

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("李四");
        row2.createCell(1).setCellValue(32);

        // 输出文件
        try (FileOutputStream fos = new FileOutputStream("导出结果.xlsx")) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        workbook.close();
    }
}
