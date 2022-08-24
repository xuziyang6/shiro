package com.xu.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomerMd5Realm extends AuthorizingRealm {

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
        System.out.println("神父信息:"+primaryPrincipal);
        //根据用户名获取当前用户的角色信息，以及权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将数据库中拿到的角色信息赋值给权限对象
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");

        return simpleAuthorizationInfo;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //在token中获取用户名
        String principal = (String)token.getPrincipal();
        System.out.println(principal);
        if ("xuziyang".equals(principal)){
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    "xuziyang",
                    "123",
                     ByteSource.Util.bytes("X0*7ps"),
                     this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
