package com.blz.springsecuritydemo.config;

import com.blz.springsecuritydemo.config.filter.JwtTokenAuthorizationFilter;
import com.blz.springsecuritydemo.config.filter.JwtTokenLoginFilter;
import com.blz.springsecuritydemo.config.handler.*;
import com.blz.springsecuritydemo.config.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Title: WebSecurityConfigurer
 * @Package: com.blz.springsecuritydemo.config
 * @Description:
 * @author: ITblz
 * @date: 2021/3/10 下午10:18
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new MyAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new MyLogoutSuccessHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new MyAuthenticationEntryPoint();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }

    @Bean
    public JwtTokenLoginFilter jwtTokenLoginFilter() throws Exception {
        JwtTokenLoginFilter jwtTokenLoginFilter = new JwtTokenLoginFilter("/login",authenticationManager());
        jwtTokenLoginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        jwtTokenLoginFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return jwtTokenLoginFilter;
    }

    @Bean
    public JwtTokenAuthorizationFilter jwtTokenAuthorizationFilter() throws Exception {
        return new JwtTokenAuthorizationFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        return new MyAccessDecisionManager();
    }

    @Bean
    public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(){
        return new MySecurityMetadataSource();
    }


    //设置UserDetailsService以及密码加密方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .formLogin()
                .loginProcessingUrl("/login").permitAll()
                .failureForwardUrl("/login").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .addFilterBefore(jwtTokenLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtTokenAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class)
                //不使用session和cookie 无状态认证机制以及REST API
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/p1","/p4").hasAnyAuthority("ROLE_QQ")
                .antMatchers("/p2","/p4").hasAnyAuthority("ROLE_WW")
                .antMatchers("/p3","/p4").hasAnyAuthority("ROLE_EE")
                .antMatchers("/user/query").hasAnyAuthority("ROLE_QUERY")
                .anyRequest()
                .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager());//决策管理器
                        o.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());//安全元数据源
                        return o;
                    }
                })
                .and();
    }
}
