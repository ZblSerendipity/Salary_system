package com.example.salary.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface PermissionMapper {

    @Select("select code from user inner join urs on user.unum = urs.uid " +
            "inner join roles on roles.role_id = urs.rid " +
            "inner join rps on roles.role_id = rps.rid " +
            "inner join permission on rps.pid = permission.id " +
            "where unum = #{unum}")
    Set<String> queryPermissionsByUnum(String unum);


}
