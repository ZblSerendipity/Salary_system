package com.example.salary.web;


import com.example.salary.domain.User;
import com.example.salary.service.MaService;
import com.example.salary.service.StuffService;
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
    @Autowired
    StuffService stuffService;

    @RequestMapping(value = "/getps")
    String checkLogin(HttpServletResponse response, @RequestParam(value = "unum")String unum,
                    @RequestParam(value = "upassword")String upassword,HttpSession session) throws Exception{

     User user = stuffService.findUserByUnum(unum);
        System.out.println(user.toString());
     try{
         stuffService.checkLogin(unum,upassword);
         System.out.println("---登录成功---");
         session.setAttribute("unum",unum);
         session.setAttribute("upassword",upassword);
         session.setAttribute("uname",user.getUname());

         return "../static/html/mainpage.html";
     }catch (Exception e){
         System.out.println("登录失败");
         return "../static/html/onload.html";
     }

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
