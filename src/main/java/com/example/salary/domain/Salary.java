package com.example.salary.domain;

public class Salary {

    private double basic;
    private double eating;
    private double performance;
    private double communication;
    private double traffic;
    private double bonus;
    private double add;

    @Override
    public String toString() {
        return "Salary{" +
                "basic=" + basic +
                ", eating=" + eating +
                ", performance=" + performance +
                ", communication=" + communication +
                ", traffic=" + traffic +
                ", bonus=" + bonus +
                ", add=" + add +
                '}';
    }
    //总工资
    public double getall(){
        return getBasic()+getBonus()+getAdd()+getCommunication()+getEating()+getPerformance()+getTraffic();
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

    public double getAdd() {
        return add;
    }

    public void setAdd(double add) {
        this.add = add;
    }

    public Salary(double basic, double eating, double performance, double communication, double traffic, double bonus, double add) {
        this.basic = basic;
        this.eating = eating;
        this.performance = performance;
        this.communication = communication;
        this.traffic = traffic;
        this.bonus = bonus;
        this.add = add;
    }

    public Salary() {
    }
}
