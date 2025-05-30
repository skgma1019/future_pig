package com.project.community.repository;

import com.project.community.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCategoryOrderByCreatedAtDesc(String category);

    List<Article> findByCategoryOrderByLikeCountDesc(String category);

    List<Article> findAllByOrderByLikeCountDesc();

    List<Article> findAllByOrderByCreatedAtDesc();

    // 제목에 키워드가 포함된 게시글 리스트 반환
    List<Article> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String keyword);

}
