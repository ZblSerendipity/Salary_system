package com.example.salary.domain;

public class Stuff {
    private String unum;//工号
    private String uname;//姓名
    private String position;//职位
    private String bankid;//银行账号
    private String pid;//身份证号
    private String gender;//性别
    private String dname;//部门
    private double basic;//工资
    private int age;//年龄
    private int absence;//缺勤次数

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "unum='" + unum + '\'' +
                ", uname='" + uname + '\'' +
                ", position='" + position + '\'' +
                ", bankid='" + bankid + '\'' +
                ", pid='" + pid + '\'' +
                ", gender='" + gender + '\'' +
                ", dname='" + dname + '\'' +
                ", basic=" + basic +
                ", age=" + age +
                ", absence=" + absence +
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public Stuff(String unum, String uname, String position, String bankid, String pid, String gender, String dname, double basic, int age, int absence) {
        this.unum = unum;
        this.uname = uname;
        this.position = position;
        this.bankid = bankid;
        this.pid = pid;
        this.gender = gender;
        this.dname = dname;
        this.basic = basic;
        this.age = age;
        this.absence = absence;
    }

    public Stuff() {
    }
}
