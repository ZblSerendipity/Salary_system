package com.example.salary.domain;

public class Stuff {
    private String unum;//工号
    private String uname;//姓名
    private String position;//职位
    private String bankid;//银行账号
    private String pid;//身份证号
    private String gender;//性别
    private double salary;//工资
    private int age;//年龄
    private int seniority;//工龄

    @Override
    public String toString() {
        return "Stuff{" +
                "unum='" + unum + '\'' +
                ", uname='" + uname + '\'' +
                ", position='" + position + '\'' +
                ", bankid='" + bankid + '\'' +
                ", pid='" + pid + '\'' +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", seniority=" + seniority +
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public Stuff(String unum, String uname, String position, String bankid, String pid, String gender, double salary, int age, int seniority) {
        this.unum = unum;
        this.uname = uname;
        this.position = position;
        this.bankid = bankid;
        this.pid = pid;
        this.gender = gender;
        this.salary = salary;
        this.age = age;
        this.seniority = seniority;
    }

    public Stuff() {
    }
}
