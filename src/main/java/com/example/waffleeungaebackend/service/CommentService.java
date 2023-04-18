package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.dto.CommentDto;
import com.example.waffleeungaebackend.dto.request.CommentRequestDto;
import com.example.waffleeungaebackend.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(CommentRequestDto commentRequestDto, Long postId, Long memberId);

    List<CommentDto> getCommentList(Long postId );
}
