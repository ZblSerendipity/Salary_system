package com.example.salary.mapper;

import com.example.salary.domain.Stuff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

@Mapper
public interface StuffMapper {
    //查询所有员工
    @Select("select distinct stuff.unum,uname,gender,position,dname,bankid,stuffwage.basic" +
            " from (stuff left join stuffwage on stuff.unum = stuffwage.unum) " +
            "left join department on department.dnum = stuff.dnum " +
            "order by stuffwage.month desc limit 0 , #{count}"

            )
    List<Stuff> queryAll(Integer count);

    //查询员工数
    @Select("select count(*) from stuff")
    Integer getStuffRows();

    //插入新员工
    @Insert("insert into stuff " +
            "values(#{unum},#{uname},#{position},#{age},#{bankid},#{pid})")
    Integer addNewStuff(String unum,String uname,Integer age,String bankid,String pid);

    @Insert("insert into stuffwage(stuffwage.unum,stuffwage.month.stuffwage.basic,stuffwage.all) " +
            "values(#{unum},#{month},#{basic},#{basic})")
    Integer newStuffWage(String unum, Date month,Double basic);
}
