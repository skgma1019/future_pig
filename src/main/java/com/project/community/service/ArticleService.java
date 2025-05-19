package com.project.community.service;

import com.project.community.entity.Article;
import com.project.community.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> getOne(Long id) {
        return articleRepository.findById(id);
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public Article update(Long id, Article newData) {
        Article article = articleRepository.findById(id).orElseThrow();
        article.setTitle(newData.getTitle());
        article.setContent(newData.getContent());
        article.setAuthor(newData.getAuthor());
        return articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}