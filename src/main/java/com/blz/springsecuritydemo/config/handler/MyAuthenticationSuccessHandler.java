package com.blz.springsecuritydemo.config.handler;

import com.blz.springsecuritydemo.config.entity.MyUserDetails;
import com.blz.springsecuritydemo.utils.JsonResponseResult;
import com.blz.springsecuritydemo.utils.JwtTokenUtil;
import com.blz.springsecuritydemo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title: MyAuthenticationSuccessHandler
 * @Package: com.blz.springsecuritydemo.config.handler
 * @Description: 登录成功后处的逻辑
 * @author: ITblz
 * @date: 2021/3/11 下午2:56
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    ResultGenerator resultGenerator;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        JsonResponseResult jsonResult = resultGenerator.getSuccessResult("登录成功!",null);
        String token = jwtTokenUtil.generateToken(userDetails);
        // 为了跨域，把token放到响应头WWW-Authenticate里
        httpServletResponse.setHeader(JwtTokenUtil.TOKEN_HEADER, token);
        jsonResult.setCode(httpServletResponse.getStatus());
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(jsonResult.toString());
        pw.flush();
        if(pw != null)
            pw.close();

    }


}
