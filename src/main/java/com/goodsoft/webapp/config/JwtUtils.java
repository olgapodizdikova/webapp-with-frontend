package com.goodsoft.webapp.config;

import com.goodsoft.webapp.entity.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtUtils {

    @Value("MaYzkSjmkzPC57L")
    private String secret;

    @Value("120")
    private Long sessionTimeout;

    public SecurityUser parseToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        Collection<GrantedAuthority> grantedAuthorities = Stream.of(((String) body.get("roles")).split(","))
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return new SecurityUser(Integer.parseInt((String) body.get("userId")), body.getSubject(), token, grantedAuthorities);
    }

    public String generateToken(SecurityUser u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");
        String roles = u.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(","));
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(sessionTimeout).atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }


}
