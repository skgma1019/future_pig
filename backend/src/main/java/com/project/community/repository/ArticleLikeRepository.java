package com.project.community.repository;
import com.project.community.entity.Article;
import com.project.community.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
    boolean existsByArticleIdAndUsername(Long articleId, String username);
    long countByArticleId(Long articleId);

    // ✅ 토글을 위한 삭제 메서드
    void deleteByArticleAndUsername(Article article, String username);
}