package com.example.javademo.mybatis.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public class JwtUtil {
    // 携带token的请求头名字
    public final static String TOKEN_HEADER = "Authorization";
    //token的前缀
    public final static String TOKEN_PREFIX = "Bearer ";
    // 默认密钥
    public final static String DEFAULT_SECRET = "123123...";
    // token有效期
    private static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;

    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

    private static String secret;

    /**
     * 创建token
     *
     * @param username 账户主体
     * @return
     */
    public static String createToken(String username) {
        Instant instant = Instant.now().plusMillis(TOKEN_EXPIRATION);
        String token = Jwts.builder()
            //设置账户主体：sub
            .setSubject(username)
            //设置签发时间：iat
            .setIssuedAt(Date.from(instant))
            //设置过期时间：exp，必须要大于签发时间
            .setExpiration(Date.from(instant))
            //签名信息，采用secret作为私钥
            .signWith(ALGORITHM, getSecret())
            .compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 解析token串为jws
     *
     * @param token
     * @return
     */
    public static Jws<Claims> decode(String token) throws ExpiredJwtException, SignatureException, UnsupportedJwtException {
        return Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token);
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        if (StringUtils.isEmpty(token))
            return null;
        String username = Jwts.parser()
            .setSigningKey(getSecret())
            .parseClaimsJws(token)
            .getBody().getSubject();
        return username;
    }

    public static String getSecret() {
        return Optional.ofNullable(secret).orElse(DEFAULT_SECRET);
    }

    public static void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

}
