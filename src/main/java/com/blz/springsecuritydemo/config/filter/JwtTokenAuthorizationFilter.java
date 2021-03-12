package com.blz.springsecuritydemo.config.filter;

import com.blz.springsecuritydemo.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Title: JwtTokenAuthorizationFilter
 * @Package: com.blz.springsecuritydemo.config.filter
 * @Description: Token认证过滤器
 * @author: ITblz
 * @date: 2021/3/11 下午6:16
 */
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    public JwtTokenAuthorizationFilter() {}

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(JwtTokenUtil.TOKEN_HEADER);
        if (!(token == null || token.length() == 0)) {

            if (jwtTokenUtil.verifyToken(token)) {

                String username = jwtTokenUtil.getClaimFromToken(token,"username",String.class);
                // 从token提取权限
                List<LinkedHashMap<String,String>> claim = jwtTokenUtil.getClaimFromToken(token,"permissions", ArrayList.class);
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (LinkedHashMap map: claim) {
                    Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry next = iterator.next();
                        authorities.add(new SimpleGrantedAuthority(String.valueOf(next.getValue())));
                    }
                }

                // 将用户信息存入 authentication，方便后续校验
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
