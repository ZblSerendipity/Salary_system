package com.example.salary.web;


import com.example.salary.service.MaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/Info")
public class InitInfo {
    @Autowired
    MaService maService;

    @RequestMapping(value = "/getInfo")void getInfo(HttpServletResponse response, HttpSession session) throws Exception{

//        String unum = session.getAttribute("unum").toString();

       String content = maService.getStuffInfo("2017110457");
        System.out.println(content);
       response.setContentType("text/json;charset=utf-8");
       response.getWriter().write(content == null?"":content);

    }
}
