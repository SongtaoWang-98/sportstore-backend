//package com.stewart.sports_store.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin() //表单登陆 1
//                //.loginPage("/login.html") //指定登陆页面
//                .loginPage("/login")
////                .loginProcessingUrl("/authentication/form")//登陆页面提交的页面 开始使用UsernamePasswordAuthenticationFilter过滤器处理请求
//                .and() //2
//                .authorizeRequests() //下面的都是授权的配置 3
//                .antMatchers("/login").permitAll()//访问此地址就不需要进行身份认证了，防止重定向死循环
//                .anyRequest() //任何请求 4
//                .authenticated() //访问任何资源都需要身份认证 5
//                .and()
//                .csrf().disable();//关闭跨站请求伪造攻击拦截
//    }
//}
