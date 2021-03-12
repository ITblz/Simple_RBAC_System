package com.blz.springsecuritydemo.config.handler;

import com.blz.springsecuritydemo.utils.JsonResponseResult;
import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title: MyAuthenticationFailureHandler
 * @Package: com.blz.springsecuritydemo.config.handler
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 下午2:56
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    ResultGenerator resultGenerator;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        JsonResponseResult jsonResult = resultGenerator.getFailResult("登录失败!用户名或密码错误！");
        jsonResult.setCode(httpServletResponse.getStatus());
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(jsonResult.toString());
        pw.flush();
        if(pw != null)
            pw.close();
    }
}
