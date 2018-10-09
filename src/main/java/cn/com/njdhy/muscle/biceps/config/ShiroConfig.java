package cn.com.njdhy.muscle.biceps.config;


import cn.com.njdhy.muscle.biceps.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author huzhihai
 * @date 2018-07-24
 * Shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置登录页面
        shiroFilterFactoryBean.setLoginUrl("/html/sys/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/html/sys/index.html");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/html/sys/unauthorized.html");

        // 配置过滤器
        Map<String, Filter> filters = new LinkedHashMap();
        shiroFilterFactoryBean.setFilters(filters);

        // 拦截器链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        filterChainDefinitionMap.put("/html/sys/login.html", "anon");
        filterChainDefinitionMap.put("/html/sys/*.html", "authc");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/swagger*", "anon");
        filterChainDefinitionMap.put("/sys/*/*", "authc");
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/*", "authc");


        //配置某个url需要某个权限码
        //filterChainDefinitionMap.put("/hello", "perms[how_are_you]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session的过期时间
        sessionManager.setGlobalSessionTimeout(108000);
        // 删除无效会话
        sessionManager.setDeleteInvalidSessions(true);
        // 是否禁用cookie
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionCookie());
        return sessionManager;
    }

    /**
     * 会话cookie模板
     *
     * @return 会话cookie模板
     */
    @Bean
    public SimpleCookie sessionCookie() {
        // 配置cookie的名称
        SimpleCookie simpleCookie = new SimpleCookie("simpleCookie");
        // 开启保护
        simpleCookie.setHttpOnly(true);
        // -1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }


    /**
     * 会话cookie的rememberMe功能
     *
     * @return 会话cookie 记住我功能
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        // 开启保护
        rememberMeCookie.setHttpOnly(true);
        // 记住我的cookie保存30天
        rememberMeCookie.setMaxAge(60 * 60 * 30);
        return rememberMeCookie;
    }

    /**
     * 记住我RememberMe管理器
     *
     * @return 记住我管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    /**
     * form表单过滤器
     *
     * @return 登录表单对象
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("userName");
        formAuthenticationFilter.setPasswordParam("pwd");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;

    }


    /**
     * 制定安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm（支持多个Realm）
        securityManager.setRealm(userRealm());
        // 会话管理器
        securityManager.setSessionManager(sessionManager());
        // 记住我RememberMe功能
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用md5进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 加密3次
        hashedCredentialsMatcher.setHashIterations(3);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

}
