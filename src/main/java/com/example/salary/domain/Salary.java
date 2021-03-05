package com.example.salary.domain;


import java.sql.Date;

public class Salary {

    private String unum;
    private String uname;
    private Date month;
    private double basic;
    private double eating;
    private double performance;
    private double communication;
    private double traffic;
    private double bonus;
    private double overtime;
    private double all;

    @Override
    public String toString() {
        return "Salary{" +
                "unum='" + unum + '\'' +
                ", uname='" + uname + '\'' +
                ", month=" + month +
                ", basic=" + basic +
                ", eating=" + eating +
                ", performance=" + performance +
                ", communication=" + communication +
                ", traffic=" + traffic +
                ", bonus=" + bonus +
                ", overtime=" + overtime +
                ", all=" + all +
                '}';
    }

    public String getUnum() {
        return unum;
    }

    public void setUnum(String unum) {
        this.unum = unum;
    }



    //总工资
    public double getall(){
        return getBasic()+getBonus()+getOvertime()+getCommunication()+getEating()+getPerformance()+getTraffic();
    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public double getEating() {
        return eating;
    }

    public void setEating(double eating) {
        this.eating = eating;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getCommunication() {
        return communication;
    }

    public void setCommunication(double communication) {
        this.communication = communication;
    }

    public double getTraffic() {
        return traffic;
    }

    public void setTraffic(double traffic) {
        this.traffic = traffic;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getAll() {
        return all;
    }

    public void setAll(double all) {
        this.all = all;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Salary(String unum, String uname, Date month, double basic, double eating, double performance, double communication, double traffic, double bonus, double overtime, double all) {
        this.unum = unum;
        this.uname = uname;
        this.month = month;
        this.basic = basic;
        this.eating = eating;
        this.performance = performance;
        this.communication = communication;
        this.traffic = traffic;
        this.bonus = bonus;
        this.overtime = overtime;
        this.all = all;
    }

    public Salary() {
    }
}
