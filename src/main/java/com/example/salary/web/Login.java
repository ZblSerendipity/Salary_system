package com.example.salary.web;


import com.example.salary.domain.User;
import com.example.salary.service.MaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/login")
public class Login {
    @Autowired
    MaService maService;

    @RequestMapping(value = "/getps")
    void checkLogin(HttpServletResponse response, @RequestParam(value = "unum")String unum,
                    @RequestParam(value = "upassword")String upassword) throws Exception{
        boolean flag = false;
        User user = maService.queryUser(unum);
        if (user.getUpassword().equals(upassword)){
            flag = true;
        }
        //密码验证错误传输0，正确传输1
        response.getWriter().write(flag == false?0:1);
    }
}
