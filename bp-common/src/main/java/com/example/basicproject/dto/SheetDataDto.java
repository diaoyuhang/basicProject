package com.example.basicproject.dto;

import java.util.List;

public class SheetDataDto {
    private String sheetName;
    private List<List<String>> data;

    public SheetDataDto() {
    }

    public SheetDataDto(String sheetName, List<List<String>> data) {
        this.sheetName = sheetName;
        this.data = data;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
