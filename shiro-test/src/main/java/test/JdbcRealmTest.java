package test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * JdbcRealm
 */
public class JdbcRealmTest {


    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:oracle:thin:@//214.4.0.39:1521/cxdb");
        dataSource.setUsername("hftrans2s");
        dataSource.setPassword("hftrans2s");
    }

    @Test
    public void testAuthentication() {

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        // 查询权限数据开关
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql = "select name from tran_merchant where no = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("5321001089", "鞠建飞");
        // 登录后的验证
        subject.login(token);
        System.out.println("login - isAuthenicated: " + subject.isAuthenticated());
        // 登出后的验证
        //subject.logout();
        //System.out.println("logout - isAuthenicated: " + subject.isAuthenticated());

        //subject.checkRole("admin");
        ////subject.checkPermission("user:delete");
        //subject.checkPermission("user:update");
    }
}
