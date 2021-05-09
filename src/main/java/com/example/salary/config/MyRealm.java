package com.example.salary.config;

import com.example.salary.domain.User;
import com.example.salary.service.PermissionService;
import com.example.salary.service.RoleService;
import com.example.salary.service.StuffService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;


/**
 * 1.创建一个类继承AuthorizingRealm（实现Realm接口的类）
 * 2.重写doGetAuthorizationInfo和doGetAuthentication方法
 * 3.
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    StuffService stuffService;
    @Resource
    RoleService roleService;
    @Resource
    PermissionService permissionService;


    public String getName()
    {
        return "myRealm";
    };

    //获取授权信息(将当前用户的角色权限信息查询出来）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户的用户名
        String unum = principalCollection.iterator().next().toString();
        //根据用户名查询当前用户的角色列表
        Set<String> rolename = roleService.queryRoleNameByUnum(unum);
        //根据用户名查询当前用户的权限列表
        Set<String> ps = permissionService.queryPermissionsByUnum(unum);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(rolename);
        info.setStringPermissions(ps);

        return info;
    }

    //获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String unum = token.getUsername();


        User user = stuffService.findUserByUnum(unum);
        if (user == null){
            return  null;
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(unum,user.getUpassword(),user.getUname());


        return info;
    }
}
