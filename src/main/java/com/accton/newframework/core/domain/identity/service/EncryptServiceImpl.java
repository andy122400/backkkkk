package com.accton.newframework.core.domain.identity.service;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
public class EncryptServiceImpl implements EncryptService {

    private static final Logger logger = LoggerFactory.getLogger(EncryptService.class);
    private static final String AUTHORITIES_KEY = "auth-role";
    private static final String USER_ID_KEY = "USER_ID_KEY";

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private final PasswordEncoder passwordEncoder;

    public EncryptServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String generateAccessToken(Long userId, String userName, String roles) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + 180* 1000);
        return Jwts.builder()
                .setSubject(userName)
                .setId(String.valueOf(userId))
                .claim(AUTHORITIES_KEY, roles)
                .claim(USER_ID_KEY, userId)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    @Override
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            logger.error("JWT expired", ex);
        } catch (IllegalArgumentException ex) {
            logger.error("Token is null, empty or only whitespace", ex);
        } catch (MalformedJwtException ex) {
            logger.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            logger.error("Signature validation failed");
        }
        return false;
    }

    @Override
    public String getUserNameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    @Override
    public String getUserIdFromToken(String token) {
        return parseClaims(token).getId();
    }

    @Override
    public String getRoles(String token) {
        return parseClaims(token).get(AUTHORITIES_KEY).toString();
    }

    @Override
    public String hashPassword(@NotNull String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matchPassword(@NotNull String rawPassword, @NotNull String hashPassword) {
        return passwordEncoder.matches(rawPassword, hashPassword);
    }


    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
