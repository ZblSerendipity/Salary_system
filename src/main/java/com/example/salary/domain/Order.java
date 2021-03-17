package com.example.salary.domain;

import java.sql.Date;

public class Order {
    private String onum;
    private String oname;
    private String mnum;
    private String uname;
    private Date subtime;
    private String status;
    private String passnum;
    private Date time;

    @Override
    public String toString() {
        return "Order{" +
                "onum='" + onum + '\'' +
                ", oname='" + oname + '\'' +
                ", mnum='" + mnum + '\'' +
                ", mname='" + uname + '\'' +
                ", subtime=" + subtime +
                ", status='" + status + '\'' +
                ", passnum='" + passnum + '\'' +
                ", time=" + time +
                '}';
    }

    public String getOnum() {
        return onum;
    }

    public void setOnum(String onum) {
        this.onum = onum;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getMnum() {
        return mnum;
    }

    public void setMnum(String mnum) {
        this.mnum = mnum;
    }

    public String getMname() {
        return uname;
    }

    public void setMname(String mname) {
        this.uname = mname;
    }

    public Date getSubtime() {
        return subtime;
    }

    public void setSubtime(Date subtime) {
        this.subtime = subtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassnum() {
        return passnum;
    }

    public void setPassnum(String passnum) {
        this.passnum = passnum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Order(String onum, String oname, String mnum, String uname, Date subtime, String status, String passnum, Date time) {
        this.onum = onum;
        this.oname = oname;
        this.mnum = mnum;
        this.uname = uname;
        this.subtime = subtime;
        this.status = status;
        this.passnum = passnum;
        this.time = time;
    }

    public Order() {
    }
}
