package com.example.salary.web;

import com.alibaba.fastjson.JSONObject;
import com.example.salary.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/financial")
public class FinancialInfo {
    @Autowired
    FinancialService financialService;
    @RequestMapping(value = "/insertStuff")void insertStuff(HttpServletResponse response, @RequestParam(value = "unum")String unum,
                                                            @RequestParam(value = "month")String month,@RequestParam(value = "basic")String basic,
                                                            @RequestParam(value = "insurance")String insurance,@RequestParam(value = "oname")String oname)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date idate = sdf.parse(month);
        java.sql.Date date = new java.sql.Date(idate.getTime());
        Integer absence = financialService.queryAbsDays(unum, date);
        //生成提交日期
        long now = System.currentTimeMillis();
        java.sql.Date time = new java.sql.Date(now);

        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        String onum =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 审查单号
        Integer content = 0;
        Integer audit = 0;
        Integer ext = 0;
        if (absence != 0 ){

             content = financialService.insertStuffWage(unum,date,Double.parseDouble(basic),Double.parseDouble(insurance),absence,onum);

        }else {
             content = financialService.insertStuffWage(unum,date,Double.parseDouble(basic),Double.parseDouble(insurance),0,onum);
        }
        audit = financialService.insertAudit(onum,oname,unum,date,"未校验");
        ext = financialService.insertExtWage(unum,date,0.00,0.00,0.00,0.00,0.00,0.00);
        response.setContentType("text/json;charset=utf-8");
        if (audit != 0 && content != 0){
            response.getWriter().write(  "1");
        }else if (audit == 0 && content != 0){
            response.getWriter().write(  "-1");
        }else if (audit == 0 && content == 0){
            response.getWriter().write(  "-2");
        }

    }
    @RequestMapping(value = "/aduitTab")String auditTab(HttpServletResponse response,
                                                        @RequestParam(value = "limit")String size,@RequestParam(value = "page")String page)throws Exception{
        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        Integer rows = financialService.queryAudRows();
        jsonObject.put("count",rows);
        jsonObject.put("data", financialService.getAll(Integer.parseInt(page),Integer.parseInt(size)));
        return jsonObject.toString();
    }
    @RequestMapping(value = "/auditing")void auditing(HttpServletResponse response, HttpSession session,
                                                      @RequestParam(value = "onum")String onum,@RequestParam(value = "status")String status)throws Exception{
        //        String unum = session.getAttribute("unum").toString();
        long now = System.currentTimeMillis();
        java.sql.Date time = new java.sql.Date(now);
        Integer content = financialService.passAudit(status,"2017110457",onum,time);
        Integer opt = financialService.insertOpt(onum,"2017110457",status,time);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(content == 0 ? "0":"1");
    }
}
