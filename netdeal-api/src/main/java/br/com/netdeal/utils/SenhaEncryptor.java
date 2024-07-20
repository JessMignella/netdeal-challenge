package br.com.netdeal.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SenhaEncryptor {
    private final PasswordEncoder passwordEncoder;

    public SenhaEncryptor() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encrypt(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
