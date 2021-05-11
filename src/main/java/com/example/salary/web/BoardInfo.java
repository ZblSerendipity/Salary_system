package com.example.salary.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.salary.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;

@RestController
@RequestMapping(value = "/BoardInfo")
public class BoardInfo {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/getAll")
    String getAll(HttpServletResponse response, HttpSession session,
                  @RequestParam(value = "limit")String size,@RequestParam(value = "page")String page)throws Exception{

        //        String unum = session.getAttribute("unum").toString();
//        String content = boardService.queryAllBoards();
        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        Integer rows = boardService.queryAllRows();
        jsonObject.put("count",rows);
        jsonObject.put("data", boardService.queryAllBoards(Integer.parseInt(page),Integer.parseInt(size)));
        return jsonObject.toString();

    }
    //删除对应公告信息
    @RequestMapping(value = "/DelRow")
    void delBoard(HttpSession session, HttpServletResponse response,
                  @RequestParam(value = "bnum")String bnum)throws Exception{
        //        String unum = session.getAttribute("unum").toString();

        Integer content = boardService.delBoard(bnum);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(content == 0 ? "0": "1");

    }
    //发布新公告
    @RequestMapping(value = "/newBoard")
    void newBoard(HttpServletResponse response,HttpSession session,
                  @RequestParam(value = "title")String title,@RequestParam(value = "content")String content)throws Exception{
                String unum = session.getAttribute("unum").toString();
        //生成公告日期
        long now = System.currentTimeMillis();
        Date time = new Date(now);

        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        String bnum =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 公告ID

        Integer flag = boardService.inNewBoard(unum,bnum,time,title,content);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(flag == 0 ? "0": "1");

    }
    //查询最新公告
    @RequestMapping(value = "/latest")
    void latestB(HttpServletResponse response)throws  Exception{
        String content = boardService.queryLatestB();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(content == null ? "0": content);
    }
}
