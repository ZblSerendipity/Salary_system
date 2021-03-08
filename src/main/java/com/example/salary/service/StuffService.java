package com.example.salary.service;


import com.example.salary.domain.Stuff;
import com.example.salary.mapper.StuffMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StuffService {
    @Resource
    StuffMapper stuffMapper;
    //查询所有员工
    public List<Stuff> queryAll(Integer count){

        return stuffMapper.queryAll(count);
    };

    //查询员工数
    public Integer getStuffRows(){
        return stuffMapper.getStuffRows();
    };
}
