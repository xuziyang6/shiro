package com.xu.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomerRealm extends AuthorizingRealm {

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //在token中获取用户名
        String principal = (String)token.getPrincipal();
        System.out.println(principal);
        if ("xuziyang".equals(principal)){
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("xuziyang","123",this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
