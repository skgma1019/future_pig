package com.project.community.controller;

import com.project.community.security.JWTUtil;
import com.project.community.service.ArticleLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleLikeController {

    private final ArticleLikeService articleLikeService;
    private final JWTUtil jwtUtil;

    @PostMapping("/{id}/like")
    public ResponseEntity<?> like(@PathVariable Long id,
                                  @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        String message = articleLikeService.toggleLike(id, username);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<?> getLikes(@PathVariable Long id) {
        long count = articleLikeService.countLikes(id);
        return ResponseEntity.ok(Map.of("likeCount", count));
    }
}
