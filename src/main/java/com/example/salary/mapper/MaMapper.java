package com.example.salary.mapper;

import com.example.salary.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaMapper {
@Select("select * from user " +
        "where unum = #{unum}")
    User getUser(String unum);
}
