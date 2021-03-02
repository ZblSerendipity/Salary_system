package com.example.salary.web;

import com.example.salary.service.MaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/web")
public class MaWeb {
    @Autowired
    MaService maService;
    @RequestMapping(value = "/main")
    void mainweb(HttpServletResponse response, HttpSession session)throws Exception{

    }
}
