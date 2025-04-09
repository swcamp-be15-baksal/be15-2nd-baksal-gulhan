package com.hanhwa_tae.gulhan.utils.jwt;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.RankType;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long jwtRefreshExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init(){
        byte[] key = Decoders.BASE64.decode(jwtSecret);
        secretKey = Keys.hmacShaKeyFor(key);
    }


    public String createAccessToken(String userId, RankType rank){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .subject(userId)
                .claim("rank", rank)
                .issuedAt(now)            // 발행일
                .expiration(expiryDate)   // 종료일
                .signWith(secretKey)     // 서명 키 설정
                .compact();
    }

     public String createRefreshToken(String userId, RankType rank){
         Date now = new Date();
         Date expiryDate = new Date(now.getTime() + jwtRefreshExpiration);
         return Jwts.builder()
                 .subject(userId)         // 제목 (기본 속성)
                 .claim("rank", rank)    // 내용 (추가 속성)
                 .issuedAt(now)             // 발행일
                 .expiration(expiryDate)    // 종료일
                 .signWith(secretKey)       // 서명 키 설정
                 .compact();
     }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new BadCredentialsException("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            throw new BadCredentialsException("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("JWT Token claims empty", e);
        }
    }
}
