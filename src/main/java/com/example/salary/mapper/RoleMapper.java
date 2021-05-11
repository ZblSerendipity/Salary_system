package com.example.salary.mapper;

import com.example.salary.domain.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleMapper {
    @Select("select role_name from user inner join urs on user.unum = urs.uid " +
            "inner join roles on roles.role_id = urs.rid " +
            "where user.unum = #{unum}")
    Set<String> queryRoleNameByUnum(String unum);

    @Select("select rid from urs " +
            "where uid = #{uid}")
    List<String> queryRole(String uid);

    @Select("select * from user " +
            "order by unum " +
            "limit #{begin},#{size}")
    List<Role> queryUser (Integer begin,Integer size);

    @Select("select count(*) from user")
    Integer queryRows();

    //初始化权限
    @Insert("insert into urs " +
            "values(#{uid},'02') ")
    Integer insertRoles(String uid);
    //删除权限
    @Delete("delete from urs " +
            "where uid = #{uid} and rid = #{rid}")
    Integer deleteURid(String uid,String rid);
    //增加权限
    @Insert("insert into urs " +
            "values(#{uid},#{rid})")
    Integer addRoles(String uid,String rid);
}
