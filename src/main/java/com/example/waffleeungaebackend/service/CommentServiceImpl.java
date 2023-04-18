package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.dto.CommentDto;
import com.example.waffleeungaebackend.dto.request.CommentRequestDto;
import com.example.waffleeungaebackend.entity.Comment;
import com.example.waffleeungaebackend.entity.Member;
import com.example.waffleeungaebackend.entity.Post;
import com.example.waffleeungaebackend.repository.CommentRepository;
import com.example.waffleeungaebackend.repository.MemberRepository;
import com.example.waffleeungaebackend.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private MemberRepository memberRepository;

    @Override
    public Comment addComment(CommentRequestDto commentRequestDto, Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("유저정보가 없습니다."));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패"));


        return commentRepository.save(commentRequestDto.toEntity(post,member));

    }

    @Override
    public List<CommentDto> getCommentList(Long postId ) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        return commentList.stream().map(Comment::toDto).collect(Collectors.toList());

    }
}
