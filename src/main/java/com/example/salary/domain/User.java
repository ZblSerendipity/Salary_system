package com.example.salary.domain;

public class User {
    private String uname;//用户名
    private String unum;//账号
    private String upassword;//密码

    public User(String uname, String unum, String upassword) {
        this.uname = uname;
        this.unum = unum;
        this.upassword = upassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", unum='" + unum + '\'' +
                ", upassword='" + upassword + '\'' +
                '}';
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUnum(String unum) {
        this.unum = unum;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUname() {
        return uname;
    }

    public String getUnum() {
        return unum;
    }

    public String getUpassword() {
        return upassword;
    }

    public User() {
    }
}
