package com.blz.springsecuritydemo.config.handler;

import com.blz.springsecuritydemo.utils.JsonResponseResult;
import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title: MyAuthenticationEntryPoint
 * @Package: com.blz.springsecuritydemo.config.handler
 * @Description: 用来处理认证异常
 * @author: ITblz
 * @date: 2021/3/11 下午2:55
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    ResultGenerator resultGenerator;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        JsonResponseResult jsonResult = resultGenerator.getFailResult(e.getMessage());
        jsonResult.setCode(httpServletResponse.getStatus());
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(jsonResult.toString());
        pw.flush();
        if(pw != null)
            pw.close();
    }
}
