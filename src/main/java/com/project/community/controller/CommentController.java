package com.project.community.controller;

import com.project.community.dto.CommentDto;
import com.project.community.entity.Comment;
import com.project.community.service.CommentService;
import com.project.community.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final JWTUtil jwtUtil;

    // 댓글 조회
    @GetMapping("/{articleId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long articleId) {
        return ResponseEntity.ok(commentService.getComments(articleId));
    }

    // 댓글 작성 (JWT 필요)
    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody CommentDto dto, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(commentService.create(dto, username));
    }

    // 댓글 삭제 (본인만)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        commentService.delete(id, username);
        return ResponseEntity.ok().build();
    }
}