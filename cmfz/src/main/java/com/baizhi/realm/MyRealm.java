package com.baizhi.realm;

import com.baizhi.shiro.entity.secUser;
import com.baizhi.shiro.mapper.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private SecUserMapper secUserMapper;
    @Autowired
    private shiroMapper shiroMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //主题赋予 角色、权限
        System.out.println("=====================");
        //通过主体 查询角色
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
/**
 * select * from sec_user where user_name = #{username}
 *  *查询出当前主体信息,查询role角色
 *  select * from sec_role where role_id in (
 *      select role_id from sec_user_role where user_id=
 *          (select user_id from sec_user where user_name=#{username}))
 *
 *  假设查询出了两个角色 super  \ admin
 *  ArrayList<String> juese = new ArrayList<>();
 *         juese.add("super");
 *         juese.add("admin");
 *
 *         //根据角色查询出了权限
 *         ArrayList<String> quanxian = new ArrayList<>();
 *         quanxian.add("admin:delete");
 *         quanxian.add("admin:update");

*/


        List<String> roles = shiroMapper.selectRole(primaryPrincipal);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        /**
         * 根据角色查询权限
         * select permission_name from sec_permission where permission_id in
         * (select permission_id from sec_role_permission where role_id in
         * (select role_id from sec_role where role_id in (
         * select role_id from sec_user_role where user_id=
         * (select user_id from sec_user where user_name='dylan'))
         * ))
         *
         */

        List<String> permissions = shiroMapper.selectPermission(primaryPrincipal);
        simpleAuthorizationInfo.addStringPermissions(permissions);
            //成功的赋予了相应的角色和权限 ， 输出出去
        return simpleAuthorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String)authenticationToken.getPrincipal();
        secUser secUser = new secUser();
        secUser.setUserName(principal);

        /**
         *   根据身份信息查询数据库：
         *
         *  *查询出当前主体信息，根据主体信息
         *  提供相关信息： 用户名、加密后的密码、盐
         *
         *  */
        List<com.baizhi.shiro.entity.secUser> secUsersList = secUserMapper.select(secUser);
            if(secUsersList.size()!=0){
                secUser secUser1 = secUsersList.get(0);
          AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,secUser1.getPassword(), ByteSource.Util.bytes(secUser1.getSalt()),this.getName());
                return authenticationInfo;
            }

        return null;
    }
}
