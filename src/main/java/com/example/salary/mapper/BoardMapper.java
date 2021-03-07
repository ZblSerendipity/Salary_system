package com.example.salary.mapper;

import com.example.salary.domain.Board;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
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
            "order by time desc " +
            "limit #{begin},#{size}")
    List<Board> queryAllBoards(Integer begin,Integer size);
    //查询所有公告条数
    @Select("select count(*) from board ")
    Integer queryAllRows();

    //删除对应公告
    @Delete("delete from board " +
            "where bnum = #{bnum}")
    Integer delBoard(String bnum);

    //插入新公告
    @Insert("insert into board " +
            "values(#{unum},#{bnum},#{time},#{title},#{content})")
    Integer inNewBoard(String unum, String bnum, Date time, String title, String content);
    //查询最新公告
    @Select("select * from board,user " +
            "where board.unum = user.unum " +
            "order by time desc " +
            "limit 0,1")
    Board queryLatestB();
}
