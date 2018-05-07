package com.imooc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


/**
 * IniRealm
 */
public class IniRealmTest {

    @Test
    public void testAuthentication1() {

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mark", "1234");
         //登录后
        subject.login(token);
        System.out.println("login - isAuthenicated: " + subject.isAuthenticated());
        //登出后
        //subject.logout();
        //System.out.println("logout - isAuthenicated: " + subject.isAuthenticated());

        subject.checkRole("admin");
        //subject.checkPermission("user:delete");
        subject.checkPermission("user:update");
    }

}
