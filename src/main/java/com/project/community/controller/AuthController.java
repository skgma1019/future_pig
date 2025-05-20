package com.project.community.controller;

import com.project.community.service.UserService;
import com.project.community.security.JWTUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.Map;
import com.project.community.entity.User;


@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {
        System.out.println("register 요청 받음: " + req);
        User user = userService.register(
                req.get("username"),
                req.get("password"),
                req.get("nickname")
        );
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        User user = userService.authenticate(req.get("username"), req.get("password"));
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}

