package com.example.salary.web;


import com.example.salary.service.MaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/Info")
public class InitInfo {
    @Autowired
    MaService maService;

    @RequestMapping("/getInfo")void getInfo(HttpServletResponse response) throws Exception{

    }
}
