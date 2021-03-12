package com.blz.springsecuritydemo.config.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: JwtTokenLoginFilter
 * @Package: com.blz.springsecuritydemo.config.filter
 * @Description: Token认证过滤器
 * @author: ITblz
 * @date: 2021/3/11 下午5:45
 */
public class JwtTokenLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtTokenLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    /**
     * 登录表单处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }
}
