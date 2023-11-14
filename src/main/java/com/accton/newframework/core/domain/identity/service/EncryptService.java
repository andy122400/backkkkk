package com.accton.newframework.core.domain.identity.service;

public interface EncryptService {
    String generateAccessToken(String userName, String roles);
    boolean validateAccessToken(String token);
    String getUserNameFromToken(String token);
    String getRoles(String token);
    String hashPassword(String password);
    boolean matchPassword(String rawPassword,String hashPassword);
}