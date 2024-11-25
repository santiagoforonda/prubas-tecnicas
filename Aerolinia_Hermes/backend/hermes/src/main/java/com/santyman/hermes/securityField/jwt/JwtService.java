package com.santyman.hermes.securityField.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.santyman.hermes.securityField.auth.LoginRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "thisisaverysecurekeyformyjwtwithhmacsha256sdsdsdfhgdfd";

    public String getToken(LoginRequest user){
        return createToken(new HashMap<>(), user);
    }

    private String createToken(Map<String,Object> extraClaims, LoginRequest user){
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getEmail())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T>T extracClaims(String token,Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getKey())
            .build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token){
        return extracClaims(token, Claims::getSubject);
    }

    public Date extracExpiration(String token){
        return extracClaims(token, Claims::getExpiration);
    }

    public Boolean validaToken(String token,UserDetails user){
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token){
        return extracExpiration(token).before(new Date());
    }
}
