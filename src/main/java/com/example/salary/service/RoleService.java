package com.example.salary.service;

import com.example.salary.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class RoleService {
    @Resource
    RoleMapper roleMapper;
    public Set<String> queryRoleNameByUnum(String unum){
        return roleMapper.queryRoleNameByUnum(unum);
    }
}
