package com.example.basicproject.controller.test;

public class SearchValue {
    private Integer type;
    private String label;
    private Integer resultCount;

    public SearchValue() {
    }

    public SearchValue(Integer type, String label, Integer resultCount) {
        this.type = type;
        this.label = label;
        this.resultCount = resultCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }
}
