package com.example.carstoreapi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component

public class JwtTokenUtil {
    private static final String secret = "jwtpassword";

    public static final long JWT_TOKEN_VALIDITY_HOUR = 5 * 60 * 60000; // 5 hours

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    // 토큰이 틀렸는지, 유효기간이 안지났는지 검사
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(String id) {
        return generateToken(id, new HashMap<>());
    }

    public String generateToken(String id, Map<String, Object> claims) {
        return doGenerateToken(id, claims);
    }

    // 토큰 생성
    private String doGenerateToken(String id, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(id) //claims에 토큰 id set
                .setIssuedAt(new Date(System.currentTimeMillis())) //claims에 토큰 생성시간 기록
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_HOUR)) //claims에 토큰 유효기간 기록
                .signWith(SignatureAlgorithm.HS512, secret) //calims에 토큰 암호화 알고리즘 설정
                .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
