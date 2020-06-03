package com.glassboxdigital.xls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class OpenFiles {

    private String date;
    private String recent;
    private String fts;
    private String Journey;
    private String total;

    public OpenFiles(String date, String recent, String fts, String journey, String total) {
        this.date = date;
        this.recent = recent;
        this.fts = fts;
        Journey = journey;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecent() {
        return recent;
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }

    public String getFts() {
        return fts;
    }

    public void setFts(String fts) {
        this.fts = fts;
    }

    public String getJourney() {
        return Journey;
    }

    public void setJourney(String journey) {
        Journey = journey;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
