package com.project.community.controller;



import com.project.community.entity.User;
import com.project.community.security.JWTUtil;
import com.project.community.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.project.community.dto.RegisterRequest; // DTO 패키지에 따라 수정 필요
import com.project.community.entity.User;
import com.project.community.service.UserService;



//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    public AuthController(UserService service, JWTUtil jwt) {
        this.userService = service;
        this.jwtUtil = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        try {
        User user = userService.register(
                request.getUsername(),
                request.getPassword(),
                request.getNickname()
        );
        return ResponseEntity.ok("회원가입 성공");
    } catch (Exception e) {
        System.out.println("회원가입 중 에러발생");
        e.printStackTrace();
        return ResponseEntity.status(500).body("회원가입 실패: " + e.getMessage());
    }
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        try {
            System.out.println("🔥 로그인 요청 도착: " + req);

            User user = userService.authenticate(req.get("username"), req.get("password"));
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            System.out.println("❌ 로그인 실패: " + e.getMessage());
            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 일치하지 않습니다.");
        } catch (Exception e) {
            System.out.println("❌ 서버 내부 오류: " + e.getMessage());
            return ResponseEntity.status(500).body("서버 오류 발생");
        }
    }
}

