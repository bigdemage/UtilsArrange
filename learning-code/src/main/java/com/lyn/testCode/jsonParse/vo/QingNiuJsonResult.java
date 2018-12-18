package com.lyn.testCode.jsonParse.vo;


import java.util.List;

public class QingNiuJsonResult<T> {

    private String totalcount;

    private List<T> items;

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
