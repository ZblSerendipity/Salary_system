package com.example.salary.web;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.salary.service.MaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@RestController
@RequestMapping(value = "/Info")
public class InitInfo {
    @Autowired
    MaService maService;

    //主页初始化用户信息
    @RequestMapping(value = "/getInfo")void getInfo(HttpServletResponse response, HttpSession session) throws Exception{

//        String unum = session.getAttribute("unum").toString();

       String content = maService.getStuffInfo("2017110457");
       response.setContentType("text/json;charset=utf-8");
       response.getWriter().write(content == null?"":content);

    }

    //修改用户信息
    @RequestMapping(value = "/updateInfo") void updateUInfo(HttpServletResponse response, HttpSession session,
                                                            @RequestParam(value = "uname")String uname,@RequestParam(value = "pid")String pid,
                                                            @RequestParam(value = "age")String age) throws Exception{
        //        String unum = session.getAttribute("unum").toString();

       boolean content = maService.updateUInfo(uname,pid,"2017110457",Integer.parseInt(age));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(content == true? "1" : "0");

    }
    //初始化头像
    @RequestMapping(value = "/person")void personImage(HttpSession session,HttpServletResponse response) throws IOException {
        String value = null;
        if (session != null) {
            value = session.getAttribute("stu_num").toString();
        } else {
            value = "default";
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("images/person/" + (value == null ?"default":value) + ".jpg"));
        } catch (IOException e) {
            fis = new FileInputStream(new File("images/person/" + "default" + ".jpg"));
        }
        int len = 0;
        response.setContentType("multipart/form-data");
        ServletOutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024 * 10];
        while ((len = fis.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        out.flush();
    };
    //修改头像
    @RequestMapping(value = "/updateImage")String updateImage(HttpSession session, MultipartFile file)throws Exception{


        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()){
            jsonObject.put("code",1);
            jsonObject.put("msg","不能上传空文件！");
            return jsonObject.toString();
        }
                String unum = session.getAttribute("unum").toString();
        if (unum == null){
            jsonObject.put("code",1);
            jsonObject.put("msg","上传失败！");
            return jsonObject.toString();
        }
        String outputFile="images/person/"+unum+".jpg";
        File outFile = new File(outputFile);
        outFile.createNewFile();
        FileOutputStream out = new FileOutputStream(outFile);
        out.write(file.getBytes());
        jsonObject.put("code",0);
        jsonObject.put("msg","上传成功！");
        out.flush();
        out.close();
        return jsonObject.toString();
    }

    //查询用户每月工资
    @RequestMapping(value = "/getUMWage") String getUMWage(HttpSession session,HttpServletResponse response,
                                                         @RequestParam(value = "limit")String size,@RequestParam(value = "page")String page) throws  Exception{

        //        String unum = session.getAttribute("unum").toString();

        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        Integer row = maService.getSize("2017110457");
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        jsonObject.put("count",row);
        jsonObject.put("data", maService.getMonthWage("2017110457",Integer.parseInt(page),Integer.parseInt(size)));
        return jsonObject.toString();

    }
    @RequestMapping(value = "/chart")void getWage(HttpSession session,HttpServletResponse response)throws Exception{
        //        String unum = session.getAttribute("unum").toString();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(maService.getYWage("2017110457")));
    }
}
