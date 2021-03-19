package com.example.salary.mapper;

import com.example.salary.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

@Mapper
public interface FinancialMapper {

    //插入对应账号stuffwage表
    @Insert("insert into stuffwage " +
            "values(#{unum},#{month},#{basic},#{insurance},#{absence},#{onum})")
    Integer insertStuffWage(String unum, Date month,double basic,double insurance,Integer absence,String onum);
    //插入对应的extrawage表
    @Insert("insert into extrawage " +
            "values(#{unum},#{date},#{eating},#{traffic},#{communication},#{performance},#{bonus},#{overtime})")
    Integer insertExtWage(String unum,Date date,Double eating,Double traffic,Double communication,Double performance,Double bonus,Double overtime);
    //查询对应账号缺勤天数
    @Select("select count(*) from absence" +
            " where unum = #{unum} and TIMESTAMPDIFF(Day,absence.date,#{date}) <= 0 and TIMESTAMPDIFF(Day,absence.date,#{date}) >= -30")
    Integer queryAbsDays(String unum , Date date);
    //插入审查单
    @Insert("insert into orders(onum,oname,mnum,subtime,status) " +
            " values(#{onum},#{oname},#{mnum},#{subtime},#{status})")
    Integer insertAudit(String onum,String oname,String mnum,Date subtime,String status);
    //查看审查单
    @Select("select * from orders,user " +
            "where orders.mnum = user.unum " +
            "order by subtime desc " +
            " limit #{begin},#{size}")
    List<Order> getAll(Integer begin,Integer size);
    //通过审查单
    @Update("update orders " +
            "set status = #{status} and passnum = #{passnum} and time = #{time}  " +
            "where onum = #{onum}")
    Integer passAudit(String status,String passnum,String onum,Date time);
    //查看审查单数量
    @Select("select count(*) from orders ")
    Integer queryAudRows();
    //记录审查表操作
    @Insert("insert into orders_detail " +
            "values(#{onum},#{unum},#{opt},#{time})")
    Integer insertOpt(String onum,String unum,String opt,Date time);
    //查询对应审查表的审核历史
    @Select("select * from orders_detail " +
            "where onum = #{onum} " +
            "order by time desc " +
            "limit #{begin},#{size}")
    Integer queryOpt(String onum,Integer begin,Integer size);
    //查询对应审查单号的工资详情
//    @Select("select * from user,stuffwage " +
//            "where user.unum = stuffwage.unum and onum = #{onum} " +
//            "order by basic desc " +
//            "limit #{begin},#{size}")
//    List<>

}
