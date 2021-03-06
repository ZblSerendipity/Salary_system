package com.example.salary.web;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.salary.service.MaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/Info")
public class InitInfo {
    @Autowired
    MaService maService;

    //主页初始化用户信息
    @RequestMapping(value = "/getInfo")void getInfo(HttpServletResponse response, HttpSession session) throws Exception{

//        String unum = session.getAttribute("unum").toString();

       String content = maService.getStuffInfo("2017110457");
       response.setContentType("text/json;charset=utf-8");
       response.getWriter().write(content == null?"":content);

    }

    //修改用户信息
    @RequestMapping(value = "/updateInfo") void updateUInfo(HttpServletResponse response, HttpSession session,
                                                            @RequestParam(value = "uname")String uname,@RequestParam(value = "pid")String pid,
                                                            @RequestParam(value = "age")String age) throws Exception{
        //        String unum = session.getAttribute("unum").toString();

       boolean content = maService.updateUInfo(uname,pid,"2017110457",Integer.parseInt(age));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(content == true? "1" : "0");

    }
    //查询用户每月工资
    @RequestMapping(value = "/getUMWage") String getUMWage(HttpSession session,HttpServletResponse response,
                                                         @RequestParam(value = "limit")String size,@RequestParam(value = "page")String page) throws  Exception{

        //        String unum = session.getAttribute("unum").toString();

        JSONObject jsonObject = new JSONObject();

        response.setContentType("text/json;charset=utf-8");
        Integer row = maService.getSize("2017110457");


        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        jsonObject.put("count",row);
        jsonObject.put("data", maService.getMonthWage("2017110457",Integer.parseInt(page),Integer.parseInt(size)));


        return jsonObject.toString();

    }
    @RequestMapping(value = "/chart")void getWage(HttpSession session,HttpServletResponse response)throws Exception{
        //        String unum = session.getAttribute("unum").toString();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(maService.getYWage("2017110457")));
    }
}
