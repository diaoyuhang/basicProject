package com.example.basicproject.dto;


import com.alibaba.fastjson2.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SheetDto{
    private String sheetName;

    private List<String> sheetCellHeadName;
    private Map<String,String> sheetCellHeadNameKey;

    private List<JSONObject> sheetCellData = new ArrayList<>();

    public Map<String, String> getSheetCellHeadNameKey() {
        return sheetCellHeadNameKey;
    }

    public void setSheetCellHeadNameKey(Map<String, String> sheetCellHeadNameKey) {
        this.sheetCellHeadNameKey = sheetCellHeadNameKey;
    }

    public List<String> getSheetCellHeadName() {
        return sheetCellHeadName;
    }

    public void setSheetCellHeadName(List<String> sheetCellHeadName) {
        this.sheetCellHeadName = sheetCellHeadName;
    }

    public List<JSONObject> getSheetCellData() {
        return sheetCellData;
    }

    public void setSheetCellData(List<JSONObject> sheetCellData) {
        this.sheetCellData = sheetCellData;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
