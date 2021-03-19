package com.example.salary.service;

import com.example.salary.domain.Order;
import com.example.salary.mapper.FinancialMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

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
    public  Integer passAudit(String status,String passnum,String onum,Date time){
        return financialMapper.passAudit(status, passnum, onum,time);
    };
    //查看审查单
    public List<Order> getAll(Integer page, Integer size){
        Integer begin = (page - 1)* size;
//        List<Order> orders = financialMapper.getAll(begin,size);
//        for (int i = 0;i < orders.size(); i++){
//            if (orders.get(i).get)
//        }
        return financialMapper.getAll(begin,size);
    };
    //查看审查单数量
    public Integer queryAudRows(){
        return financialMapper.queryAudRows();
    };
    //插入对应的extrawage表
    public  Integer insertExtWage(String unum,Date date,Double eating,Double traffic,Double communication,Double performance,Double bonus,Double overtime){
        return financialMapper.insertExtWage(unum, date, eating, traffic, communication, performance, bonus, overtime);
    };
    //记录审查表操作
    public Integer insertOpt(String onum,String unum,String opt,Date time){
        return  financialMapper.insertOpt(onum, unum, opt, time);
    };
    //查询对应审查表的审核历史
    public Integer queryOpt(String onum,Integer page,Integer size){
        Integer begin = (page - 1)* size;
        return financialMapper.queryOpt(onum,begin,size);
    };
}
