package com.example.salary.service;

import com.alibaba.fastjson.JSON;
import com.example.salary.domain.Board;
import com.example.salary.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BoardService {
    @Resource
    BoardMapper boardMapper;

    //查询所有公告
    public List<Board> queryAllBoards(){
        return boardMapper.queryAllBoards();
    };
    //查询所有公告条数
    public Integer queryAllRows(){
        return boardMapper.queryAllRows();
    };
    //删除对应公告
    public Integer delBoard(String bnum){
        return boardMapper.delBoard(bnum);
    };
}
