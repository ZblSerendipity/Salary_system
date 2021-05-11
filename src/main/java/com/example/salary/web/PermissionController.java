package com.example.salary.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.salary.service.PermissionService;
import com.example.salary.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Roles")
public class PermissionController {
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/getRoles")String getRoles(HttpServletResponse response, @RequestParam(value = "limit")String size
            , @RequestParam(value = "page")String page)throws Exception{
        JSONObject jsonObject = new JSONObject();
        response.setContentType("text/json;charset=utf-8");
        Integer rows = roleService.queryRows();
        jsonObject.put("code",0 );
        jsonObject.put("msg","");
        jsonObject.put("count",rows);
        jsonObject.put("data",roleService.queryUser(Integer.parseInt(page),Integer.parseInt(size)));

        return jsonObject.toString();

    }
    @RequestMapping("/getPermission")void getPermission(HttpServletResponse response,@RequestParam(value = "unum")String unum)throws Exception{

        response.setContentType("text/json;charset=utf-8");
        Set<String> set = permissionService.queryPermissionsByUnum(unum);
        String content = JSON.toJSONString(set);
        response.getWriter().write(content == null ? "0": content);

    }
    @RequestMapping("/add")void add(HttpServletResponse response,@RequestParam(value = "unum")String unum,
                                    @RequestParam(value = "role")String role)throws Exception{
        response.setContentType("text/json;charset=utf-8");
        List<String> list = roleService.queryRole(unum);
        Boolean flag = true;
        for (int i = 0; i < list.size() ; i ++){
            System.out.println(list.get(i));
            if (list.get(i).equals(role)){
                flag = false;
            }
        }
        if (flag){
            Integer content = roleService.addRoles(unum,role);
            System.out.println(content);
            response.getWriter().write(content == 0 ? "0": content.toString());
        }else {
            response.getWriter().write("-1");
        }
    }
    @RequestMapping(value = "/del")void del(HttpServletResponse response,@RequestParam(value = "unum")String unum,
                                            @RequestParam(value = "role")String role)throws Exception{
        response.setContentType("text/json;charset=utf-8");
        List<String> list = roleService.queryRole(unum);
        Boolean flag = true;

        for (int i = 0; i < list.size() ; i ++){

            if (list.get(i).equals(role)){
                flag = false;
            }
        }
        if (!flag){
            Integer content = roleService.deleteURid(unum,role);

            response.getWriter().write(content == 0 ? "0": content.toString());
        }else {
            response.getWriter().write("-1");
        }
    }
}
