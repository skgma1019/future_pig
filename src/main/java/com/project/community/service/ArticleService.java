package com.project.community.service;

import com.project.community.dto.ArticleDto;
import com.project.community.entity.Article;
import com.project.community.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor  // 생성자 주입을 자동 생성
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 전체 게시글 조회
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    // 특정 게시글 조회
    public Optional<Article> getOne(Long id) {
        return articleRepository.findById(id);
    }

    // 게시글 생성 (작성자 JWT에서 추출)
    public Article create(ArticleDto dto, String username) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setAuthor(username);
        return articleRepository.save(article);
    }

    // 게시글 수정 (작성자만 가능)
    public Article update(Long id, ArticleDto dto, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        if (!article.getAuthor().equals(username)) {
            throw new RuntimeException("작성자만 수정할 수 있습니다.");
        }

        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        return articleRepository.save(article);
    }

    // 게시글 삭제 (작성자만 가능)
    public void delete(Long id, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        if (!article.getAuthor().equals(username)) {
            throw new RuntimeException("작성자만 삭제할 수 있습니다.");
        }

        articleRepository.deleteById(id);
    }
}
