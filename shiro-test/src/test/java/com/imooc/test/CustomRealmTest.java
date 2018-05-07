package com.imooc.test;

import com.imooc.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomRealmTest {
    @Test
    public void testAuthentication1() {

        CustomRealm customRealm = new CustomRealm();

        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mark", "123");
        //登录后
        subject.login(token);
        System.out.println("login - isAuthenicated: " + subject.isAuthenticated());
        //登出后
        //subject.logout();
        //System.out.println("logout - isAuthenicated: " + subject.isAuthenticated());

        //subject.checkRole("admin");
        //subject.checkPermission("user:delete");
        //subject.checkPermission("user:update");

        subject.checkRole("admin");
        subject.checkPermissions("user:add", "user:delete");
    }
}
