package com.cpbank.user.cmd.api.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
