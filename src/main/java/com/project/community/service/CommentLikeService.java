package com.project.community.service;

import com.project.community.entity.Comment;
import com.project.community.entity.CommentLike;
import com.project.community.repository.CommentLikeRepository;
import com.project.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public String toggleLike(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));

        // 이미 좋아요를 누른 경우 → 취소
        if (commentLikeRepository.existsByCommentIdAndUsername(commentId, username)) {
            commentLikeRepository.deleteByCommentIdAndUsername(commentId, username);
            return "좋아요가 취소되었습니다.";
        }

        // 아직 누르지 않은 경우 → 추가
        CommentLike like = new CommentLike();
        like.setComment(comment);
        like.setUsername(username);
        commentLikeRepository.save(like);

        return "좋아요 완료!";
    }


    public long countLikes(Long commentId) {
        return commentLikeRepository.countByCommentId(commentId);
    }
}
