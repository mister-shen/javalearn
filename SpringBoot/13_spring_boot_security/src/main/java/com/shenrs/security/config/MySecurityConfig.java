package com.shenrs.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        // 定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll() // 所有人都可访问
                .antMatchers("/level1/**").hasRole("VIP1") // 指定角色访问
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登录功能，效果，如果没有登录，没有权限就会来到登录页面
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/userlogin");
        // 1./login 来到登录页面
        // 2.重定向到/login?error表示登录失败
        // 3.更多详细规定
        // 4.默认post形式的/login代表处理登录
        // 5.一旦定制loginpage;那么loginpage的post请求就代表登录

        // 开启自动配置的注销功能
        http.logout()
        .logoutSuccessUrl("/"); // 定义注销成功来到首页
        // 1. 访问/logout 表示用户注销，清空session
        // 2. 注销成功会返回 /login?logout 页面

        // 开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        // 登录成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie,只要通过检查就可以免登录。
        // 注销成功后会删除cookie
    }

    /**
     * 定义认证规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("zhangsan").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP2", "VIP3")
                .and()
                .withUser("wanger").password("123456").roles("VIP1", "VIP3");

    }
}
