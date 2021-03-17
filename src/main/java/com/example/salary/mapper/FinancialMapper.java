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
    //查询对应账号缺勤天数
    @Select("select count(*) from absence" +
            " where unum = #{unum} and TIMESTAMPDIFF(Day,absence.date,#{date}) <= 0 and TIMESTAMPDIFF(Day,absence.date,#{date}) >= -30")
    Integer queryAbsDays(String unum , Date date);
    //插入审查单
    @Insert("insert into order(onum,oname,mnum,subtime,status) " +
            "values(#{onum},#{oname},#{mnum},#{subtime},#{status})")
    Integer insertAudit(String onum,String oname,String mnum,Date subtime,String status);
    //查看审查单
    @Select("select * from order,user " +
            "where order.mnum = user.unum ")
    List<Order> getAll();
    //通过审查单
    @Update("update order " +
            "set status = #{status} and passnum = #{passnum} " +
            "where onum = #{onum}")
    Integer passAudit(String status,String passnum,String onum);

}
