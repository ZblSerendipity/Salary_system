package com.example.salary.domain;

public class Role {
    private String unum;
    private String uname;
    private String role;

    @Override
    public String toString() {
        return "Role{" +
                "unum='" + unum + '\'' +
                ", uname='" + uname + '\'' +
                ", role='" + role + '\'' +
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role(String unum, String uname, String role) {
        this.unum = unum;
        this.uname = uname;
        this.role = role;
    }

    public Role() {
    }
}
