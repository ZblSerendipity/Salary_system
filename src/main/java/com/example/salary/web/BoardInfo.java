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

@RestController
@RequestMapping(value = "/BoardInfo")
public class BoardInfo {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/getAll")
    String getAll(HttpServletResponse response, HttpSession session)throws Exception{

        //        String unum = session.getAttribute("unum").toString();
//        String content = boardService.queryAllBoards();
        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(content == null ? "0": content);
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        Integer rows = boardService.queryAllRows();
        jsonObject.put("count",rows);
        jsonObject.put("data", boardService.queryAllBoards());
        return jsonObject.toString();

    }
    @RequestMapping(value = "/DelRow")
    void delBoard(HttpSession session, HttpServletResponse response,
                  @RequestParam(value = "bnum")String bnum)throws Exception{
        //        String unum = session.getAttribute("unum").toString();

        Integer content = boardService.delBoard(bnum);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(content == 0 ? "0": "1");

    }
}
