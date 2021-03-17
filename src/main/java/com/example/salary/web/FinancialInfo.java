package com.example.salary.web;

import com.example.salary.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
        if (absence != 0 ){

             content = financialService.insertStuffWage(unum,date,Double.parseDouble(basic),Double.parseDouble(insurance),absence,onum);

        }else {
             content = financialService.insertStuffWage(unum,date,Double.parseDouble(basic),Double.parseDouble(insurance),0,onum);
        }
        audit = financialService.insertAudit(onum,oname,unum,date,"0");
        response.setContentType("text/json;charset=utf-8");
        if (audit != 0 && content != 0){
            response.getWriter().write(  "1");
        }else if (audit == 0 && content != 0){
            response.getWriter().write(  "-1");
        }else if (audit == 0 && content == 0){
            response.getWriter().write(  "-2");
        }


    }
}
