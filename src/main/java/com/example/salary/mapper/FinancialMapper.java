package com.example.salary.mapper;

import com.example.salary.domain.Extra;
import com.example.salary.domain.Order;
import com.example.salary.domain.Salary;
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
    //插入对应的detail_extrawage表
    @Insert("insert into detail_extrawage " +
            "values(#{onum},#{unum},#{date},#{type},#{sum})")
    Integer insertExtra(String onum,String unum,Date date,String type,Double sum);
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
    //查询对应审查单号的基本工资详情
    @Select("select * from user,stuffwage " +
            "where user.unum = stuffwage.unum and onum = #{onum} " +
            "order by basic desc " +
            "limit #{begin},#{size}")
    List<Salary> getAudWage(String onum,Integer begin,Integer size);

    //查询对应审查单号的补贴单详情
    @Select(("select * from user,detail_extrawage " +
            "where user.unum = detail_extrawage.unum and onum = #{onum} " +
            "order by sum desc " +
            "limit #{begin},#{size}"))
    List<Extra> getExtraWage(String onum,Integer begin,Integer size);

    @Select(("select * from user,detail_extrawage " +
            "where user.unum = detail_extrawage.unum and onum = #{onum} " +
            "order by sum desc " ))
    List<Extra> getExtraWage(String onum);
    //更新extrawage
    @Update("update extrawage" +
            " set #{type} = #{sum} " +
            "where unum = #{unum} and TIMESTAMPDIFF(Day,date,#{date}) <= 30 and TIMESTAMPDIFF(Day,date,#{date}) >= 0")
    Integer updExtraWage(String type,Double sum,String unum,Date date);
    //更新orders
//    @Update("update orders " +
//            "set status = #{status} " +
//            "where onum = #{onum}")
//    Integer updOrders(String status,String onum);

}
