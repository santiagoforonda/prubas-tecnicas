package com.santyman.hermes.securityField.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.santyman.hermes.securityField.config.UserInfoService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    
    private final UserInfoService userInfoSerive;

    public JwtAuthFilter(JwtService jwtService, UserInfoService userInfoSerive) {
        this.jwtService = jwtService;
        this.userInfoSerive = userInfoSerive;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
           String authHeader = request.getHeader("Authorization");
           String token = null;
           String username = null;
           if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username=jwtService.extractUsername(token);
           }
           if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetais = userInfoSerive.loadUserByUsername(username);
            if(jwtService.validaToken(token, userDetais)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetais,null,userDetais.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                }
           }
           filterChain.doFilter(request, response);
    }
}