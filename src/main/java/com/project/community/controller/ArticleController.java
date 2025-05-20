package com.project.community.controller;

import com.project.community.dto.ArticleDto;
import com.project.community.entity.Article;
import com.project.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Article> getArticle(@PathVariable Long id) {
        return articleService.getOne(id);
    }

    @PostMapping
    public Article createArticle(@RequestBody ArticleDto dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setAuthor(dto.getAuthor());

        return articleService.create(article);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody ArticleDto dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setAuthor(dto.getAuthor());

        return articleService.update(id, article);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
    }

    // 🔍 확인용 테스트 API (선택)
    @PostMapping("/test")
    public String testJson(@RequestBody ArticleDto dto) {
        System.out.println("📥 title: " + dto.getTitle());
        System.out.println("📥 content: " + dto.getContent());
        System.out.println("📥 author: " + dto.getAuthor());
        return "OK";
    }
}

