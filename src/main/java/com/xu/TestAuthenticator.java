package com.xu;

import com.xu.realm.CustomerMd5Realm;
import com.xu.realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class TestAuthenticator {
    public static void main(String[] args) {
        //1.创建安全管理器对象
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //2.给安全管理器设置realm
        CustomerMd5Realm customerMd5Realm = new CustomerMd5Realm();
        //HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //使用算法
        //credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数1
        //credentialsMatcher.setHashIterations(1024);
        //customerMd5Realm.setCredentialsMatcher(credentialsMatcher);
        defaultSecurityManager.setRealm(customerMd5Realm);

        //3.SecurityUtils 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //4.关键对象 subject 主体
        Subject subject = SecurityUtils.getSubject();

        //5.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xuziyang","123");
        try {
            subject.login(token);
            subject.hasRole("admin");

        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        System.out.println("正常执行完毕");
    }

}
