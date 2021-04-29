package com.example.salary.web;

import com.alibaba.fastjson.JSONObject;
import com.example.salary.domain.Extra;
import com.example.salary.domain.Salary;
import com.example.salary.service.FinancialService;
import com.example.salary.service.MaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/financial")
public class FinancialInfo {
    @Autowired
    FinancialService financialService;
    @Autowired
    MaService maService;


    //查询用户每月工资
    @RequestMapping(value = "/getUMWage") String getUMWage(HttpServletResponse response,@RequestParam(value = "unum")String unum,
                                                           @RequestParam(value = "limit")String size,@RequestParam(value = "page")String page) throws  Exception{


        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        Integer row = maService.getSize(unum);
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        jsonObject.put("count",row);
        jsonObject.put("data", maService.getMonthWage(unum,Integer.parseInt(page),Integer.parseInt(size)));
        return jsonObject.toString();

    }

    //插入工资信息并生成审核单
    @RequestMapping(value = "/insertStuff")void insertStuff(HttpServletResponse response, HttpSession session,
                                                            @RequestParam(value = "unum")String unum,
                                                            @RequestParam(value = "month")String month,@RequestParam(value = "basic")String basic,
                                                            @RequestParam(value = "insurance")String insurance,@RequestParam(value = "oname")String oname)throws Exception{

                String mnum = session.getAttribute("unum").toString();
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
        audit = financialService.insertAudit(onum,oname,mnum,time,"未校验");
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
    //按上月发放工资
    @RequestMapping(value = "/lastMonthWages") void lastMonthWages(HttpServletResponse response,HttpSession session)throws Exception{

        String mnum = session.getAttribute("unum").toString();
        response.setContentType("text/json;charset=utf-8");
        long now = System.currentTimeMillis();
        Date date = new Date();
        java.sql.Date start = new java.sql.Date(now);
        java.sql.Date finish = new java.sql.Date(now);


        System.out.println(finish);
        //判断当前时间和获取时间是否相差一个月，满足条件则发放，不满足则不发放
        List<Salary> list = financialService.queryLastMonth(start);
        start = list.get(0).getMonth();
        Calendar sta = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();

        sta.setTime(start);
        fin.setTime(finish); 

        LocalDate startdate = LocalDate.of(sta.get(Calendar.YEAR),sta.get(Calendar.MONTH)+1,sta.get(Calendar.DATE));
        LocalDate finaltdate = LocalDate.of(fin.get(Calendar.YEAR),fin.get(Calendar.MONTH)+1,fin.get(Calendar.DATE));

        //查询日期与数据库工资表最新发放时间间隔
        long daysDiff = ChronoUnit.DAYS.between(startdate,finaltdate);

        if (daysDiff <20){
            response.getWriter().write(  "-1");

        }
        else {
            sta.add(sta.MONTH,1);
            date = sta.getTime();
            start = new java.sql.Date(date.getTime());


            for (int i = 0; i < list.size(); i++){
                list.get(i).setMonth(start);
            }
            int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
            int r2=(int)(Math.random()*(10));
            String onum ="B"+String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 审查单号
            for (int i = 0; i < list.size(); i++){
                Integer absence = financialService.queryAbsDays(list.get(i).getUnum(), list.get(i).getMonth());
                if (absence != 0 ){

                    financialService.insertStuffWage(list.get(i).getUnum(), list.get(i).getMonth(),list.get(i).getBasic(),list.get(i).getInsurance(),absence,onum);

                }else {
                    financialService.insertStuffWage(list.get(i).getUnum(), list.get(i).getMonth(),list.get(i).getBasic(),list.get(i).getInsurance(),0,onum);
                }

                financialService.insertExtWage(list.get(i).getUnum(), list.get(i).getMonth(),0.00,0.00,0.00,0.00,0.00,0.00);
            }
            financialService.insertAudit(onum,"按上月工资发放",mnum,finish,"未校验");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(  "1");
        }

    };
    //插入补贴信息并生成审核单
    @RequestMapping(value = "/extra")void insertExtra(HttpServletResponse response,@RequestParam(value = "unum")String unum,
                                                      @RequestParam(value = "date")String date,@RequestParam(value = "sum")String sum,
                                                      @RequestParam(value = "type")String type,@RequestParam(value = "oname")String oname,
                                                      HttpSession session)throws Exception{

                String mnum = session.getAttribute("unum").toString();
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
        Integer audit = financialService.insertAudit(onum,oname,mnum,ntime,"未校验");
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
    @RequestMapping(value = "/detail")String detail(HttpServletResponse response, HttpSession session,
                                                      @RequestParam(value = "onum")String onum,@RequestParam(value = "limit")String size,
                                                  @RequestParam(value = "page")String page)throws Exception{
        //        String unum = session.getAttribute("unum").toString();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        response.setContentType("text/json;charset=utf-8");
        Integer rows = 0;
        if (onum.substring(0,1).equals("B")){
            rows = financialService.getAudWageRows(onum);
            jsonObject.put("count",rows);
            jsonObject.put("data", financialService.getAudWage(onum,Integer.parseInt(page),Integer.parseInt(size)));
        }else if (onum.substring(0,1).equals("E")){
            rows = financialService.getExtraWageRows(onum);
            jsonObject.put("count",rows);
            jsonObject.put("data", financialService.getExtraWage(onum,Integer.parseInt(page),Integer.parseInt(size)));
        }
        System.out.println(jsonObject);
        return jsonObject.toString();


    }
    //审核逻辑
    @RequestMapping(value = "/howAudit")void howAudit(HttpServletResponse response,HttpSession session,
                                                      @RequestParam(value = "onum")String onum, @RequestParam(value = "flag")String flag)throws Exception{
        String unum = session.getAttribute("unum").toString();
        //审核时间
        long now = System.currentTimeMillis();
        java.sql.Date time = new java.sql.Date(now);
        Integer upd = 0;
        Integer updExtDetail = 0;
        Integer updOpt = 0;
        response.setContentType("text/json;charset=utf-8");
        //判断哪一类审核单
        if (onum.substring(0,1).equals("B")){
            if (flag.equals("pass")){
                upd = financialService.passAudit("已通过",unum,onum,time);
                updOpt = financialService.insertOpt(onum,unum,"通过",time);

            }else if (flag.equals("back")){
                upd = financialService.passAudit("已退回",unum,onum,time);
                updOpt = financialService.insertOpt(onum,unum,"退回",time);
            }
        //补贴单通过后更新extrawage表，，更新审核表和操作表
        }else if (onum.substring(0,1).equals("E")){
            if (flag.equals("pass")){
                List<Extra> list = financialService.getExtraWages(onum);
                System.out.println(list);
                for (int i = 0;i < list.size();i++){
                    financialService.updExtraWage(list.get(i).getType(),list.get(i).getSum(),list.get(i).getUnum(),list.get(i).getDate());
                    updExtDetail++;
                }
                upd = financialService.passAudit("已通过",unum,onum,time);
                updOpt = financialService.insertOpt(onum,unum,"通过",time);
            }else if (flag.equals("back")){
                upd = financialService.passAudit("已退回",unum,onum,time);
                updOpt = financialService.insertOpt(onum,unum,"退回",time);
            }

        }else {

            response.getWriter().write("-1");

        }

        if (upd !=0 && updOpt !=0){
            response.getWriter().write("1");
        }else if (upd ==0 && updOpt !=0){
            //出错
            response.getWriter().write("0");
        }else if (upd ==0 && updOpt ==0){
            //插入操作出错
            response.getWriter().write("-2");
        }

    }
}
