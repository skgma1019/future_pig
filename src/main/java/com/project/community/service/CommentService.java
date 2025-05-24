package com.project.community.service;

import com.project.community.dto.CommentDto;
import com.project.community.entity.Article;
import com.project.community.entity.Comment;
import com.project.community.repository.ArticleRepository;
import com.project.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<Comment> getComments(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public Comment create(CommentDto dto, String username) {
        Article article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));

        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setAuthor(username);
        comment.setContent(dto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public void delete(Long id, String username) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글 없음"));
        if (!comment.getAuthor().equals(username)) {
            throw new RuntimeException("본인의 댓글만 삭제할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }
}