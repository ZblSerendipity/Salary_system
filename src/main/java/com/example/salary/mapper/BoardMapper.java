package com.example.salary.mapper;

import com.example.salary.domain.Board;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {
    //按工号查询发布的公告
    @Select("select * from board,user " +
            "where board.unum = user.unum and board.unum = #{unum}" +
            " order by time desc")
    List<Board> queryBoardsByUnum(String unum);
    //查询所有公告
    @Select("select * from board,user " +
            "where board.unum = user.unum " +
            "order by time desc")
    List<Board> queryAllBoards();
    //查询所有公告条数
    @Select("select count(*) from board ")
    Integer queryAllRows();

    //删除对应公告
    @Delete("delete from board " +
            "where bnum = #{bnum}")
    Integer delBoard(String bnum);
}
