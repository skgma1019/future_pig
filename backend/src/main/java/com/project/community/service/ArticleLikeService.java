package com.project.community.service;

import com.project.community.entity.Article;
import com.project.community.entity.ArticleLike;
import com.project.community.repository.ArticleLikeRepository;
import com.project.community.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleLikeService {

    private final ArticleLikeRepository articleLikeRepository;
    private final ArticleRepository articleRepository;

    public String toggleLike(Long articleId, String username) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        if (articleLikeRepository.existsByArticleIdAndUsername(articleId, username)) {
            articleLikeRepository.deleteByArticleAndUsername(article, username);
            return "좋아요가 취소되었습니다.";
        }

        ArticleLike like = new ArticleLike();
        like.setArticle(article);
        like.setUsername(username);
        articleLikeRepository.save(like);

        return "좋아요 완료!";
    }


    public long countLikes(Long articleId) {
        return articleLikeRepository.countByArticleId(articleId);
    }
}
