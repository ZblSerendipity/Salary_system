package com.example.salary.service;

import com.example.salary.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class PermissionService {
    @Resource
    PermissionMapper permissionMapper;
    public Set<String> queryPermissionsByUnum(String unum){
        return permissionMapper.queryPermissionsByUnum(unum);
    }

}
