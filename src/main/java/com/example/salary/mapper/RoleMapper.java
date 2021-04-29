package com.example.salary.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper {
    @Select("select role_name from user inner join urs on user.unum = urs.uid " +
            "inner join roles on roles.role_id = urs.rid " +
            "where user.unum = #{unum}")
    Set<String> queryRoleNameByUnum(String unum);
}
