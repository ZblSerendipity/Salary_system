package com.example.salary.service;

import com.example.salary.domain.Extra;
import com.example.salary.domain.Order;
import com.example.salary.domain.Salary;
import com.example.salary.mapper.FinancialMapper;
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
    //插入对应的detail_extrawage表
    public  Integer insertExtra(String onum,String unum,Date date,String type,Double sum){
        return financialMapper.insertExtra(onum, unum, date, type, sum);
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
    //查询对应审查单号的基本工资详情
    public List<Salary> getAudWage(String onum, Integer page, Integer size){
        Integer begin = (page - 1)* size;
        return  financialMapper.getAudWage(onum, begin, size);
    };

    public Integer getAudWageRows(String onum){
        return financialMapper.getAudWageRows(onum);
    };
    //查询对应审查单号的补贴单详情
    public List<Extra> getExtraWage(String onum, Integer page, Integer size){
        Integer begin = (page - 1)* size;
        List<Extra> list = financialMapper.getExtraWage(onum, begin, size);
        for (int i = 0; i < list.size(); i++){
            switch (list.get(i).getType()){
                case "overtime":
                    list.get(i).setType("加班费");
                    break;
                case "eating":
                    list.get(i).setType("餐补");
                    break;
                case "traffic":
                    list.get(i).setType("交通补贴");
                    break;
                case "communication":
                    list.get(i).setType("通讯补贴");
                    break;
                case "performance":
                    list.get(i).setType("绩效");
                    break;
                case  "bonus":
                    list.get(i).setType("奖金");
                    break;

            }


        }
        return list;
    };
    public Integer getExtraWageRows(String onum){
        return financialMapper.getExtraWageRows(onum);
    };
    public List<Extra> getExtraWages(String onum){
        return financialMapper.getExtraWages(onum);
    };
    //更新extrawage
    public Integer updExtraWage(String type,Double sum,String unum,Date date){
        return financialMapper.updExtraWage(type, sum, unum, date);
    };
    //更新orders
//    public Integer updOrders(String status,String onum){
//        return financialMapper.updOrders(status, onum);
//    };
}
