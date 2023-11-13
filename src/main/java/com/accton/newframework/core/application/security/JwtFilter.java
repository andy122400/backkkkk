package com.accton.newframework.core.application.security;

import com.accton.newframework.core.domain.identity.service.EncryptService;
import com.accton.newframework.utility.HttpUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final EncryptService jwtTokenService;

    public JwtFilter(EncryptService jwtTokenService){
        this.jwtTokenService = jwtTokenService;
    }

    private void setAuthenticationContext(String userName, String authoritiesStr, String token,HttpServletRequest request) {
        Collection<? extends GrantedAuthority> authorities = authoritiesFromStr(authoritiesStr);
        UserDetails userDetails = new User(userName, "", authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Collection<? extends GrantedAuthority> authoritiesFromStr(String str) {
        if (ObjectUtils.isEmpty(str)) return new ArrayList<>();
        return Arrays
                .stream(str.split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = HttpUtil.getToken(request);
        if (ObjectUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!jwtTokenService.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userName = jwtTokenService.getUserNameFromToken(token);
        String authoritiesStr = jwtTokenService.getRoles(token);
        setAuthenticationContext(userName, authoritiesStr, token,request);
        filterChain.doFilter(request, response);
    }
}
