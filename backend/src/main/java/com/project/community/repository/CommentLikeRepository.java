package com.project.community.repository;

import com.project.community.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByCommentIdAndUsername(Long commentId, String username);
    long countByCommentId(Long commentId);

    void deleteByCommentIdAndUsername(Long commentId, String username);

}
