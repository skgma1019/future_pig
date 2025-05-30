package com.project.community.controller;

import com.project.community.dto.ArticleDto;
import com.project.community.entity.Article;
import com.project.community.service.ArticleService;
import com.project.community.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final JWTUtil jwtUtil;

    // 모든 게시글 조회
    @GetMapping
    public List<Article> getArticles(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "latest") String sort
    ) {
        if (category != null && !category.isEmpty()) {
            return articleService.getByCategory(category, sort);
        } else {
            return articleService.getAllSorted(sort);
        }
    }



    // 특정 게시글 조회 (id 기반)
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        return articleService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 게시글 작성 (JWT 인증 필요)
    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleDto dto, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", ""); // 헤더에서 토큰 추출
        String username = jwtUtil.extractUsername(token);   // 토큰으로부터 사용자명 추출
        Article created = articleService.create(dto, username); // 게시글 생성
        return ResponseEntity.ok(created);
    }

    // 게시글 수정 (작성자 본인만 가능)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleDto dto, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        Article updated = articleService.update(id, dto, username); // 게시글 수정
        return ResponseEntity.ok(updated);
    }

    // 게시글 삭제 (작성자 본인만 가능)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        articleService.delete(id, username); // 게시글 삭제
        return ResponseEntity.ok().build();
    }


    @GetMapping("/search")
    public List<Article> searchArticles(@RequestParam String keyword){
        return articleService.searchByTitle(keyword);
    }



}