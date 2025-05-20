package com.project.community.service;

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

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> getOne(Long id) {
        return articleRepository.findById(id);
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    // 수정
    public Article update(Long id, Article newData) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        article.setTitle(newData.getTitle());
        article.setContent(newData.getContent());
        article.setAuthor(newData.getAuthor());
        return articleRepository.save(article);
    }

    // 삭제
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
