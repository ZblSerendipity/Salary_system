package com.example.salary.service;

import com.example.salary.domain.Role;
import com.example.salary.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Resource
    RoleMapper roleMapper;
    public Set<String> queryRoleNameByUnum(String unum){
        return roleMapper.queryRoleNameByUnum(unum);
    }
    public List<Role> queryUser (Integer page, Integer size){
        Integer begin = (page - 1)* size;
        List<Role> list = roleMapper.queryUser(begin,size);
        for (int i = 0 ; i < list.size() ; i ++){
            list.get(i).setRole(roleMapper.queryRoleNameByUnum(list.get(i).getUnum()));
        }
        return list;
    };
    public Integer queryRows(){
        return roleMapper.queryRows();
    };
    public Integer insertRoles(String uid){
        return roleMapper.insertRoles(uid);
    };
    //删除权限
    public Integer deleteURid(String uid,String rid){
        return  roleMapper.deleteURid(uid, rid);
    };
    //增加权限
    public Integer addRoles(String uid,String rid){
        return roleMapper.addRoles(uid, rid);
    };

    public List<String> queryRole(String uid){
        return roleMapper.queryRole(uid);
    };
}
