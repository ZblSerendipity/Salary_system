package com.example.salary.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/html/onload.html")
    public String login(){
        return "../static/html/onload";
    }
//    @RequestMapping("/")
//    public String login1(){
//        return "login";
//    }
    @RequestMapping("/html/boardManage.html")
    public String boardManagePage()
    {
        return "../static/html/boardManage";
    }
    @RequestMapping("/html/boardPage.html")
    public String boardPage()
    {
        return "../static/html/boardPage";
    }
    @RequestMapping("/html/salaryManagePage.html")
    public String salaryManagePage(){return "../static/html/salaryManagePage";}
    @RequestMapping("/html/salaryPage.html")
    public String salaryPage(){return "../static/html/salaryPage";}
    @RequestMapping("/html/stuffManagePage.html")
    public String stuffManage(){return "../static/html/stuffManagePage";}
    @RequestMapping("/html/mainpage.html")
    public String mainPage(){return "../static/html/mainpage";}
    @RequestMapping("/html/modifyPage.html")
    public String modiPage(){return "../static/html/modifyPage";}
    @RequestMapping("/html/powerPage.html")
    public String powerPage(){return "../static/html/powerPage";}
}
