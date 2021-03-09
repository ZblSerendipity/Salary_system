package com.example.salary.web;

import com.alibaba.fastjson.JSONObject;
import com.example.salary.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/stuff")
public class StuffInfo {
    @Autowired
    StuffService stuffService;
    //获取所有员工信息
    @RequestMapping(value = "/getAll") String getAll(HttpServletResponse response, HttpSession session,
                                                     @RequestParam(value = "limit")String size, @RequestParam(value = "page")String page)throws Exception{
        //        String unum = session.getAttribute("unum").toString();

        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        Integer rows = stuffService.getStuffRows();
        jsonObject.put("count",rows);
        jsonObject.put("data",stuffService.queryAll(rows));

        return jsonObject.toString();
    }
    //删除员工
    @RequestMapping(value = "/delStuff")void delStuff(HttpSession session,HttpServletResponse response,
                                                        @RequestParam(value = "unum")String unum_del)throws Exception{
        //        String unum = session.getAttribute("unum").toString();

        Integer flag = stuffService.delStuff(unum_del);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(flag != 0?"1":"0");

    }
    //插入新员工
    @RequestMapping(value = "/addNewStuff")void addNewStuff(HttpSession session,HttpServletResponse response,
                                                            @RequestParam(value = "unum")String unum,@RequestParam(value = "uname")String uname,
                                                            @RequestParam(value = "age")String age,@RequestParam(value = "position")String position,
                                                            @RequestParam(value = "pid")String pid,@RequestParam(value = "bankid")String bankid,
                                                            @RequestParam(value = "gender")String gender,@RequestParam(value = "dnum")String dnum)throws Exception{

        //        String unum = session.getAttribute("unum").toString();
        Integer flag = stuffService.addNewStuff(unum,uname,Integer.parseInt(age),position,bankid,pid,dnum,gender);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(flag != 0?"1":"0");
    }
}
