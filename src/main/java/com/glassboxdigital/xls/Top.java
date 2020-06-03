package com.glassboxdigital.xls;

public class Top {

    private String date;
    private String clingineCPU;
    private String clingineMEM;
    private String clickHouseCPU;
    private String clickHouseMEM;
    private String kafkaCPU;
    private String kafkaMEM;

    public Top(String date, String clingineCPU, String clingineMEM, String clickHouseCPU, String clickHouseMEM, String kafkaCPU, String kafkaMEM) {
        this.date = date;
        this.clingineCPU = clingineCPU;
        this.clingineMEM = clingineMEM;
        this.clickHouseCPU = clickHouseCPU;
        this.clickHouseMEM = clickHouseMEM;
        this.kafkaCPU = kafkaCPU;
        this.kafkaMEM = kafkaMEM;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClingineCPU() {
        return clingineCPU;
    }

    public void setClingineCPU(String clingineCPU) {
        this.clingineCPU = clingineCPU;
    }

    public String getClingineMEM() {
        return clingineMEM;
    }

    public void setClingineMEM(String clingineMEM) {
        this.clingineMEM = clingineMEM;
    }

    public String getClickHouseCPU() {
        return clickHouseCPU;
    }

    public void setClickHouseCPU(String clickHouseCPU) {
        this.clickHouseCPU = clickHouseCPU;
    }

    public String getClickHouseMEM() {
        return clickHouseMEM;
    }

    public void setClickHouseMEM(String clickHouseMEM) {
        this.clickHouseMEM = clickHouseMEM;
    }

    public String getKafkaCPU() {
        return kafkaCPU;
    }

    public void setKafkaCPU(String kafkaCPU) {
        this.kafkaCPU = kafkaCPU;
    }

    public String getKafkaMEM() {
        return kafkaMEM;
    }

    public void setKafkaMEM(String kafkaMEM) {
        this.kafkaMEM = kafkaMEM;
    }
}

