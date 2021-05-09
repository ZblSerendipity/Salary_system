package com.example.salary.web;


import com.example.salary.domain.User;
import com.example.salary.service.MaService;
import com.example.salary.service.StuffService;
import org.apache.shiro.crypto.hash.Md5Hash;
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
                String unum = session.getAttribute("unum").toString();
        System.out.println(password_new);
        System.out.println(password_old);
                String oldPass = maService.queryUser(unum).getUpassword();
                Md5Hash old = new Md5Hash(password_old);
        System.out.println(oldPass);
        System.out.println(old.toString());

        if (old.toString().equals(oldPass)){

            Md5Hash md5Hash = new Md5Hash(password_new);
            String newpass = md5Hash.toString();
            System.out.println(newpass);
            Integer content = maService.updpsw(newpass,unum);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(content != 0?"1":"0");

        }
    }
}
