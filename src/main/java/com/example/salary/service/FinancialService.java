package com.example.salary.service;

import com.example.salary.mapper.FinancialMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;

@Service
public class FinancialService {
    @Resource
    FinancialMapper financialMapper;

    //插入对应账号stuffwage表
    public Integer insertStuffWage(String unum, Date month, double basic, double insurance, Integer absence, String onum){
        return financialMapper.insertStuffWage(unum, month, basic, insurance, absence, onum);
    };
    //查询对应账号缺勤天数
    public  Integer queryAbsDays(String unum , Date date){
        return financialMapper.queryAbsDays(unum, date);
    };
    //插入审查单
    public Integer insertAudit(String onum, String oname, String mnum, Date subtime, String status){
        return financialMapper.insertAudit(onum, oname, mnum, subtime, status);
    };
    //通过审查单
    public  Integer passAudit(String status,String passnum,String onum){
        return financialMapper.passAudit(status, passnum, onum);
    };
}
