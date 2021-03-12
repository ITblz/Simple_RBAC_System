package com.blz.springsecuritydemo.config.handler;

import com.blz.springsecuritydemo.utils.JsonResponseResult;
import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title: MyAccessDeniedHandler
 * @Package: com.blz.springsecuritydemo.config.handler
 * @Description: 这个用来处理授权异常。
 * @author: ITblz
 * @date: 2021/3/11 下午2:55
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    ResultGenerator resultGenerator;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
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
