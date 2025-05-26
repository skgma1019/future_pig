package com.project.community.controller;

import com.project.community.security.JWTUtil;
import com.project.community.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;
    private final JWTUtil jwtUtil;

    @PostMapping("/{id}/like")
    public ResponseEntity<?> like(@PathVariable Long id,
                                  @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        String message = commentLikeService.toggleLike(id, username);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<?> getLikes(@PathVariable Long id) {
        long count = commentLikeService.countLikes(id);
        return ResponseEntity.ok(Map.of("likeCount", count));
    }
}
