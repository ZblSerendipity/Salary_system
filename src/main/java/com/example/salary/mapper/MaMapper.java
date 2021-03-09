package com.example.salary.mapper;

import com.example.salary.domain.Salary;
import com.example.salary.domain.Stuff;
import com.example.salary.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MaMapper {
@Select("select * from user " +
        "where unum = #{unum}")
    User getUser(String unum);

@Update("update user" +
        " set upassword = #{upassword} " +
        "where unum = #{unum}")
    Integer updpsw(String upassword,String unum);

//查询用户信息
@Select("select * from stuff,stuffwage,department " +
        "where stuff.unum = stuffwage.unum and stuff.dnum = department.dnum and stuff.unum = #{unum} " +
        "order by month desc limit 0,1")
    Stuff getStuffInfo(String unum);

    //修改用户信息
    @Update("update stuff set uname = #{uname} , age = #{age} , pid = #{pid}" +
            "where unum = #{unum}")
    boolean updateUInfo(String uname,String pid,String unum,Integer age);

    //查询用户每月工资信息
    @Select("select * from stuffwage,user " +
            "where user.unum = #{unum} and user.unum = stuffwage.unum " +
            "order by month desc " +
            "limit #{begin},#{size}")
    List<Salary> getMonthWage(@Param("unum")String unum,@Param("begin")Integer begin,@Param("size")Integer size);
    //查询用户工资信息条数
    @Select("select count(*) from stuffwage " +
            "where unum = #{unum}")
    Integer getSize(@Param("unum")String unum);
    //查询过去至多12月的工资信息
    @Select("select * from stuffwage,user " +
            "where user.unum = #{unum} and user.unum = stuffwage.unum " +
            "order by month desc " +
            "limit 0,12")
    List<Salary> getYWage(@Param("unum")String unum);

}




