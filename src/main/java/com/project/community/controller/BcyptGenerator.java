package com.project.community.controller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcyptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "qwer"; // 원하는 비밀번호
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("🔐 암호화된 비밀번호: " + encodedPassword);
    }
}
