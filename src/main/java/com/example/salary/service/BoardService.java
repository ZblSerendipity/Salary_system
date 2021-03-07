package com.example.salary.service;

import com.alibaba.fastjson.JSON;
import com.example.salary.domain.Board;
import com.example.salary.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class BoardService {
    @Resource
    BoardMapper boardMapper;

    //查询所有公告
    public List<Board> queryAllBoards(Integer page,Integer size ){
        Integer begin = (page - 1)* size;
        return boardMapper.queryAllBoards(begin,size);
    };
    //查询所有公告条数
    public Integer queryAllRows(){
        return boardMapper.queryAllRows();
    };
    //删除对应公告
    public Integer delBoard(String bnum){
        return boardMapper.delBoard(bnum);
    };
    //插入新公告
    public Integer inNewBoard(String unum, String bnum, Date time, String title, String content){
        return boardMapper.inNewBoard(unum, bnum, time, title, content);
    };
    //查询最新公告
    public String queryLatestB(){
        return JSON.toJSONString(boardMapper.queryLatestB());
    };
}
