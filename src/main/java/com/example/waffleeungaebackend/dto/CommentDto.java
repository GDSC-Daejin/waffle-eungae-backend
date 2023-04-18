package com.example.waffleeungaebackend.dto;

import com.example.waffleeungaebackend.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long commentId;

    private String content;

    private Integer like;

    private LocalDateTime createDate;

    private Long postId;

    private Member member;



}
