package com.example.salary.domain;

import java.sql.Date;

public class Board {
    private String unum;
    private String bnum;
    private String uname;
    private Date time;
    private String content;

    public String getBnum() {
        return bnum;
    }

    public void setBnum(String bnum) {
        this.bnum = bnum;
    }

    @Override
    public String toString() {
        return "Board{" +
                "unum='" + unum + '\'' +
                ", bnum='" + bnum + '\'' +
                ", uname='" + uname + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Board(String unum, String bnum, String uname, Date time, String content) {
        this.unum = unum;
        this.bnum = bnum;
        this.uname = uname;
        this.time = time;
        this.content = content;
    }

    public Board() {
    }
}
