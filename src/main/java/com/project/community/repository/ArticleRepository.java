package com.project.community.repository;

import com.project.community.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedAtDesc();        // 최신순
    List<Article> findAllByOrderByLikeCountDesc();
}
