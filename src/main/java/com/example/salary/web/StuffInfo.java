package com.example.salary.web;

import com.alibaba.fastjson.JSONObject;
import com.example.salary.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/stuff")
public class StuffInfo {
    @Autowired
    StuffService stuffService;
    //获取所有员工信息
    @RequestMapping(value = "/getAll") String getAll(HttpServletResponse response, HttpSession session,
                                                     @RequestParam(value = "limit")String size, @RequestParam(value = "page")String page)throws Exception{
//                String unum = session.getAttribute("unum").toString();

        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        Integer rows = stuffService.getStuffRows();
        jsonObject.put("count",rows);
        jsonObject.put("data",stuffService.queryAll(rows));

        return jsonObject.toString();
    }
    @RequestMapping(value = "/getStuff")String getStuff(HttpServletResponse response,@RequestParam(value = "info")String info,
                                                        @RequestParam(value = "limit")String size, @RequestParam(value = "page")String page)throws Exception{

        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        Integer rows = 0;
        if (info.equals("all")){
            rows = stuffService.getStuffRows();
            jsonObject.put("count",rows);
            jsonObject.put("data",stuffService.queryAll(rows));
        }else {
            String unum  = stuffService.queryUnumByUname(info);
            if (unum == null){
                jsonObject.put("count",1);
                jsonObject.put("data",stuffService.queryStuff(info));
            }else {
                jsonObject.put("count",1);
                jsonObject.put("data",stuffService.queryStuff(unum));
            }

        }
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


        Integer flag = stuffService.addNewStuff(unum,uname,Integer.parseInt(age),position,bankid,pid,dnum,gender);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(flag != 0?"1":"0");
    }
    //缺勤登记
    @RequestMapping(value = "/absence")void absence(HttpSession session,HttpServletResponse response,
                                                    @RequestParam(value = "date")String date,@RequestParam(value = "absname")String name
                                                    )throws Exception{
        //        String unum = session.getAttribute("unum").toString();
        String absnum  = stuffService.queryNumByName(name);
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date abdate = sdf.parse(date);
            java.sql.Date absdate = new java.sql.Date(abdate.getTime());

            if (absnum.equals("")||absnum == null){
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().write("0");
            }else {
                Integer flag = stuffService.insertAbs(absnum,absdate);
                if (flag != 0){
                    Integer rows = stuffService.queryAbsDays(absnum,absdate);
                    System.out.println(rows);
                    Integer content  = stuffService.updateAbs(absnum,absdate,rows);
                    System.out.println(content);
                    response.setContentType("text/json;charset=utf-8");
                    response.getWriter().write(content != 0 ?"1":"-2");
                }
            }

        }catch (ParseException e){
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write("-1");//日期格式错误
        }


    };

    //查询所有缺勤人员
    @RequestMapping(value = "/queryallAbs")String queryAllAbs(HttpServletResponse response,HttpSession session,
                                                              @RequestParam(value = "limit")String size,@RequestParam(value = "page")String page)throws Exception{
        //        String unum = session.getAttribute("unum").toString();
        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        Integer rows = stuffService.queryAbsTabRows();
        jsonObject.put("count",rows);
        jsonObject.put("data", stuffService.queryAllAbs(Integer.parseInt(page),Integer.parseInt(size)));
        return jsonObject.toString();

    };
    //根据工号查询缺勤信息
    @RequestMapping(value = "/queryAbsByNum")String queryAbsByNum(HttpSession session,HttpServletResponse response,
                                                                  @RequestParam(value = "limit")String size,@RequestParam(value = "page")String page,
                                                                  @RequestParam(value = "unum")String unum)throws Exception{
        //        String unum = session.getAttribute("unum").toString();
        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        Integer rows = stuffService.queryAbsRowsByUnum(unum);

        jsonObject.put("count",rows);
        jsonObject.put("data", stuffService.queryAbsByNum(unum,Integer.parseInt(page),Integer.parseInt(size)));
        return jsonObject.toString();

    }
    @RequestMapping(value = "/test")void test(HttpServletResponse response)throws Exception{
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("1");
    }

}
