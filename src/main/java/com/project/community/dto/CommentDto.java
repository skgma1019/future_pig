package com.project.community.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CommentDto {
    private Long articleId;
    private String content;
}