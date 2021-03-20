package com.example.salary.web;

import com.alibaba.fastjson.JSONObject;
import com.example.salary.domain.Extra;
import com.example.salary.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/financial")
public class FinancialInfo {
    @Autowired
    FinancialService financialService;
    //插入工资信息并生成审核单
    @RequestMapping(value = "/insertStuff")void insertStuff(HttpServletResponse response, HttpSession session,
                                                            @RequestParam(value = "unum")String unum,
                                                            @RequestParam(value = "month")String month,@RequestParam(value = "basic")String basic,
                                                            @RequestParam(value = "insurance")String insurance,@RequestParam(value = "oname")String oname)throws Exception{

        //        String mnum = session.getAttribute("unum").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date idate = sdf.parse(month);
        java.sql.Date date = new java.sql.Date(idate.getTime());
        Integer absence = financialService.queryAbsDays(unum, date);
        //生成提交日期
        long now = System.currentTimeMillis();
        java.sql.Date time = new java.sql.Date(now);

        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        String onum ="B"+String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 审查单号
        Integer content = 0;
        Integer audit = 0;
        Integer ext = 0;
        if (absence != 0 ){

             content = financialService.insertStuffWage(unum,date,Double.parseDouble(basic),Double.parseDouble(insurance),absence,onum);

        }else {
             content = financialService.insertStuffWage(unum,date,Double.parseDouble(basic),Double.parseDouble(insurance),0,onum);
        }
        audit = financialService.insertAudit(onum,oname,"2017110445",time,"未校验");
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
    //插入补贴信息并生成审核单
    @RequestMapping(value = "/extra")void insertExtra(HttpServletResponse response,@RequestParam(value = "unum")String unum,
                                                      @RequestParam(value = "date")String date,@RequestParam(value = "sum")String sum,
                                                      @RequestParam(value = "type")String type,@RequestParam(value = "oname")String oname)throws Exception{

        //        String mnum = session.getAttribute("unum").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date idate = sdf.parse(date);
        java.sql.Date time = new java.sql.Date(idate.getTime());
        //生成提交日期
        long now = System.currentTimeMillis();
        java.sql.Date ntime = new java.sql.Date(now);

        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        String onum ="E"+String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 审查单号
        Integer content = financialService.insertExtra(onum,unum,time,type,Double.parseDouble(sum));
        Integer audit = financialService.insertAudit(onum,oname,"2017110445",ntime,"未校验");
        response.setContentType("text/json;charset=utf-8");
        if (audit != 0 && content != 0){
            response.getWriter().write(  "1");
        }else if (audit == 0 && content != 0){
            response.getWriter().write(  "-1");
        }else if (audit == 0 && content == 0){
            response.getWriter().write(  "-2");
        }

    }
    //待审核表（提交者）
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
    //审核表（上级）
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
    //审核逻辑
    @RequestMapping(value = "/howAudit")void howAudit(HttpServletResponse response,HttpSession session,
                                                      @RequestParam(value = "onum")String onum, @RequestParam(value = "flag")String flag)throws Exception{
//        String unum = session.getAttribute("unum").toString();
        //审核时间
        long now = System.currentTimeMillis();
        java.sql.Date time = new java.sql.Date(now);
        Integer upd = 0;
        Integer updExtDetail = 0;
        Integer updOpt = 0;
        //判断哪一类审核单
        if (onum.substring(0,1).equals("B")){
            if (flag.equals("pass")){
                upd = financialService.passAudit("已通过","2017110457",onum,time);
                updOpt = financialService.insertOpt(onum,"2017110457","通过",time);

            }else if (flag.equals("back")){
                upd = financialService.passAudit("已退回","2017110457",onum,time);
                updOpt = financialService.insertOpt(onum,"2017110457","退回",time);
            }

        }else if (onum.substring(0,1).equals("E")){
            if (flag.equals("pass")){
                List<Extra> list = financialService.getExtraWage(onum);
                for (int i = 0;i < list.size();i++){
                    financialService.updExtraWage(list.get(i).getType(),list.get(i).getSum(),list.get(i).getUnum(),list.get(i).getDate());
                    updExtDetail++;
                }
                upd = financialService.passAudit("已通过","2017110457",onum,time);
                updOpt = financialService.insertOpt(onum,"2017110457","通过",time);
            }else if (flag.equals("back")){
                upd = financialService.passAudit("已退回","2017110457",onum,time);
                updOpt = financialService.insertOpt(onum,"2017110457","退回",time);
            }

        }else {

        }

    }
}
