package com.example.salary.domain;


import java.sql.Date;

public class Salary {

    private String unum;//员工号
    private String uname;//员工名
    private Date month;//日期
    private double basic;//基本工资
    private double eating;//餐补
    private double performance;//绩效
    private double communication;//通讯补贴
    private double traffic;//交通补贴
    private double bonus;//奖金
    private double overtime;//加班
    private double insurance;//五险一金
    private double tax;//个人所得税
    private double all;//应得工资
    private double infact;//实得工资
    private double extra;//补贴
    private int absence;//缺勤次数

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
                ", insurance=" + insurance +
                ", tax=" + tax +
                ", all=" + all +
                ", infact=" + infact +
                ", extra=" + extra +
                ", absence=" + absence +
                '}';
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra() {
        this.extra = this.getPerformance() + this.getEating() + this.getCommunication()+ this.getTraffic();
    }

    public double getTax() {
        return tax;
    }

    public void setTax() {
        if (this.getAll() <= 5000){
            this.tax = 0;
        }else {
            this.tax = (this.getAll() - 5000) * 0.1;
        }
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public String getUnum() {
        return unum;
    }

    public void setUnum(String unum) {
        this.unum = unum;
    }


    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getInfact() {
        return infact;
    }

    public void setInfact() {
        this.infact = this.getAll() - this.getTax() - this.getInsurance();
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

    public void setAll() {
        this.all = this.getBasic() + this.getBonus() + this.getExtra() + this.getOvertime() - this.getAbsence() * 50 ;
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

    public Salary(String unum, String uname, Date month, double basic, double eating, double performance, double communication, double traffic, double bonus, double overtime, double insurance, int absence) {
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
        this.insurance = insurance;



    }



    public Salary() {
    }
}
