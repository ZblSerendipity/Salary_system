package com.example.salary.service;


import com.alibaba.fastjson.JSON;
import com.example.salary.domain.Salary;
import com.example.salary.domain.Stuff;
import com.example.salary.domain.User;
import com.example.salary.mapper.MaMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaService {
    @Resource
    MaMapper maMapper;
    //查询员工信息
//    public String getAllInfo(String useId){
//
//        return   JSON.toJSONString(maMapper.query());
//    }

    //查询用户
    public User queryUser(String unum){
        return maMapper.getUser(unum);
    }

    //查询该用户信息
    public String getStuffInfo(String unum){
        return JSON.toJSONString(maMapper.getStuffInfo(unum));
    }

    //修改用户信息
    public boolean updateUInfo(String uname,String pid,String unum,Integer age){
        return maMapper.updateUInfo(uname,pid,unum,age);
    }
    //查询用户每月工资信息
    public List<Salary> getMonthWage(String unum, Integer page, Integer size){
        Integer begin = (page - 1) *size;
        return maMapper.getMonthWage(unum,begin,size);
    };
    //查询用户工资信息条数
    public Integer getSize(String unum){
        return maMapper.getSize(unum);
    };

    //查询过去至多12月的工资信息
    public List<Salary> getYWage(String unum){
        return maMapper.getYWage(unum);
    };
}

