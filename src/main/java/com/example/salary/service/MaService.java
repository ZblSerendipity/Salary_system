package com.example.salary.service;


import com.example.salary.domain.User;
import com.example.salary.mapper.MaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}

