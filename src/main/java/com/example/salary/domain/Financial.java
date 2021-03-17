package com.example.salary.domain;

import java.sql.Date;

public class Financial {
    private String unum;
    private String uname;
    private String gender;
    private String department;
    private String position;
    private String bankid;
    private double wage;

    @Override
    public String toString() {
        return "Financial{" +
                "unum='" + unum + '\'' +
                ", uname='" + uname + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", bankid='" + bankid + '\'' +
                ", wage=" + wage +
                ", status='" + status + '\'' +
                '}';
    }

    private String status;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Financial(String unum, String uname, String gender, String department, String position, String bankid, double wage, String status) {
        this.unum = unum;
        this.uname = uname;
        this.gender = gender;
        this.department = department;
        this.position = position;
        this.bankid = bankid;
        this.wage = wage;
        this.status = status;
    }

    public Financial() {
    }
}
