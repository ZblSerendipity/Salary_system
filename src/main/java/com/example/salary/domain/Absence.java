package com.example.salary.domain;

import java.sql.Date;

public class Absence {
    private String unum;
    private String uname;
    private Date date;

    @Override
    public String toString() {
        return "Absence{" +
                "unum='" + unum + '\'' +
                ", uname='" + uname + '\'' +
                ", date=" + date +
                '}';
    }

    public String getUnum() {
        return unum;
    }

    public void setUnum(String unum) {
        this.unum = unum;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Absence(String unum, String uname, Date date) {
        this.unum = unum;
        this.uname = uname;
        this.date = date;
    }

    public Absence() {
    }
}
