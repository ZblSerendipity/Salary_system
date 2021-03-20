package com.example.salary.domain;


import java.sql.Date;

public class Extra {
    private String onum;
    private String unum;
    private Date date;
    private String type;
    private Double sum;

    @Override
    public String toString() {
        return "Extra{" +
                "onum='" + onum + '\'' +
                ", unum='" + unum + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", sum=" + sum +
                '}';
    }

    public Extra(String onum, String unum, Date date, String type, Double sum) {
        this.onum = onum;
        this.unum = unum;
        this.date = date;
        this.type = type;
        this.sum = sum;
    }

    public String getOnum() {
        return onum;
    }

    public void setOnum(String onum) {
        this.onum = onum;
    }

    public String getUnum() {
        return unum;
    }

    public void setUnum(String unum) {
        this.unum = unum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Extra() {
    }
}
