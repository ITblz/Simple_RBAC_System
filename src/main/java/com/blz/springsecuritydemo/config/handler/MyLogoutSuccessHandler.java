package com.blz.springsecuritydemo.config.handler;

import com.blz.springsecuritydemo.utils.JsonResponseResult;
import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title: MyLogoutSuccessHandler
 * @Package: com.blz.springsecuritydemo.config.handler
 * @Description:
 * @author: ITblz
 * @date: 2021/3/11 下午2:57
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    ResultGenerator resultGenerator;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setHeader("Authorization","");
        JsonResponseResult jsonResult = resultGenerator.getSuccessResult("退出登录成功！",null);
        jsonResult.setCode(httpServletResponse.getStatus());
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(jsonResult.toString());
        pw.flush();
        if(pw != null)
            pw.close();
    }
}
