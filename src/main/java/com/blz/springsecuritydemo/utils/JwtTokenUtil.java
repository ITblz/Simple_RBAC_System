package com.blz.springsecuritydemo.utils;

import com.blz.springsecuritydemo.config.entity.MyUserDetails;
import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * @Title: JwtTokenUtil
 * @Package: com.blz.springsecuritydemo.utils
 * @Description: Jwt工具类
 * @author: ITblz
 * @date: 2021/3/11 下午4:06
 */
@Component
public class JwtTokenUtil {

    // 携带token的请求头名字
    public final static String TOKEN_HEADER = "Authorization";
    // 默认密钥
    public final static String DEFAULT_SECRET = "MySecret";
    // 用户身份
    private final static String PERMISSIONS_CLAIM = "permissions";
    // token有效期,单位分钟；
    private final static long EXPIRE_TIME = 5 * 60 * 1000;
    // 设置Remember-me功能后的token有效期
    private final static long EXPIRE_TIME_REMEMBER = 7 * 24 * 60 * 60 * 1000;
    private final static Long expirationTimeInSecond = 1209600L;

    /**
     * 生产Token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {

        Date createdTime = new Date();
        Date expirationTime = this.getExpirationTime();
        String id = String.valueOf(((MyUserDetails)userDetails).getId());
        // 提取角色，转为List<String>对象，写入token
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return Jwts.builder()
                .setAudience(id)
                .setSubject(userDetails.getUsername())
                .setExpiration(this.getExpirationTime())
                .setIssuedAt(new Date())
                .claim("permissions",authorities)
                .claim("username",userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS512,DEFAULT_SECRET + "ITBlz")
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 获得Token中的载荷
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(DEFAULT_SECRET+"ITBlz")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Token invalided.");
        }
    }

    /**
     * 通过key获得token中载荷的值
     * @param token
     * @return
     */
    public <T>T getClaimFromToken(String token, String key, Class<T> var) {
        return getClaimsFromToken(token).get(key,var);
    }

    /**
     * 获得有效期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }

    /**
     * 校验token是否在有效期内
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + expirationTimeInSecond * 1000);
    }

    /**
     * 校验Token
     * @param token
     * @return
     */
    public boolean verifyToken(String token){
        return !isTokenExpired(token);
    }

}
