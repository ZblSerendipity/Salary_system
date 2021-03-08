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
        System.out.println(stuffService.queryAll(rows));

        return jsonObject.toString();
    }
}
