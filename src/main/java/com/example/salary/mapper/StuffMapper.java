package com.example.salary.mapper;

import com.example.salary.domain.Absence;
import com.example.salary.domain.Stuff;
import org.apache.ibatis.annotations.*;

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
            "values(#{unum},#{uname},#{position},#{dnum},#{age},#{bankid},#{pid},#{gender})")
    Integer addNewStuff(String unum,String uname,String position,Integer age,String bankid,String pid,String dnum,String gender);

    @Insert("insert into stuffwage(stuffwage.unum,stuffwage.month.stuffwage.basic,stuffwage.all) " +
            "values(#{unum},#{month},#{basic},#{basic})")
    Integer newStuffWage(String unum, Date month,Double basic);

    //删除员工
    @Delete("delete from stuff " +
            "where unum = #{unum}")
    Integer delStuff(String unum);

    //根据姓名查找员工号
    @Select("select unum from stuff" +
            " where uname = #{uname}")
    String queryNumByName(String uname);
    //插入缺勤信息
    @Insert("insert into absence " +
            "values(#{unum},#{date})")
    Integer insertAbs(String unum, Date date);

    @Update("update stuffwage " +
            "set absence = #{count} " +
            "where unum = #{unum} and TIMESTAMPDIFF(Day,stuffwage.month,#{date}) <= 30 and TIMESTAMPDIFF(Day,stuffwage.month,#{date}) <= 0 ")
    Integer updateAbs(String unum,Date date,Integer count);

    @Select("select count(*) from absence" +
            " where unum = #{unum} and TIMESTAMPDIFF(Day,absence.date,#{date}) <= 0 and TIMESTAMPDIFF(Day,absence.date,#{date}) >= -30")
    Integer queryAbsDays(String unum , Date date);

    //缺勤人员查询
    @Select("select * from user,absence " +
            "where user.unum = absence.unum " +
            "order by date desc " +
            "limit #{begin},#{size}")
    List<Absence> queryAllAbs(Integer begin,Integer size);
    //缺勤表条数查询
    @Select("select count(*) from absence ")
    Integer queryAbsTabRows();
    //对应缺勤人员查询
    @Select("select * from user,absence " +
            "where user.unum = #{unum} and user.unum = absence.unum " +
            "order by date desc " +
            "limit #{begin},#{size}")
    List<Absence> queryAbsByNum(String unum,Integer begin,Integer size);
    //对应缺勤人员缺勤数查询
    @Select("select count(*) from absence " +
            "where unum = #{unum}")
    Integer queryAbsRowsByUnum(String unum);


}
