package com.goodsoft.webapp.config;

import com.goodsoft.webapp.entity.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JwtFilter extends OncePerRequestFilter {

    public static final String TOKEN_HEADER = "auth_token";

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    @Value("MaYzkSjmkzPC57L")
    private String secretKey;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = httpServletRequest.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer")) {
                final String token = authHeader.substring(7); // The part after "Bearer "
                SecurityUser user = jwtUtils.parseToken(token);
                //regenerate new token
                SecurityContextHolder.getContext()
                        .setAuthentication(
                                new UsernamePasswordAuthenticationToken(
                                        new SecurityUser(
                                                user.getId(), user.getUsername(), token, Collections.emptyList()), token, user.getAuthorities()));
                httpServletResponse.setHeader(TOKEN_HEADER, jwtUtils
                        .generateToken(user));
            }
        } catch (Exception e) {
            LOGGER.debug("Token is invalid or expired", e);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

