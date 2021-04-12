package com.example.salary.web;


import com.example.salary.domain.User;
import com.example.salary.service.MaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "/modpwd")
    void modpwd(HttpServletResponse response, HttpSession session,
                @RequestParam(value = "password_old")String password_old,@RequestParam(value = "password_new")String password_new)throws Exception{
        //        String unum = session.getAttribute("unum").toString();
        if (password_old.equals(maService.queryUser("2017110457").getUpassword())){
            Integer content = maService.updpsw(password_new,"2017110457");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(content != 0?"1":"0");

        }
    }
}
