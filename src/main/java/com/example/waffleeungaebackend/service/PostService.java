package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.dto.PostDto;
import com.example.waffleeungaebackend.dto.request.PostCreateRequestDto;
import com.example.waffleeungaebackend.dto.request.PostPatchRequestDto;
import com.example.waffleeungaebackend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface PostService {

    Post findById(Long id);

    Post addPostList(PostCreateRequestDto postCreateRequestDto, Long categoryId, Long memberId) throws IOException;

    void deletePostList(Long id, Long MemberId);

    Post updateById(Long id, PostPatchRequestDto postPatchRequestDto, Long memberID);

    Page<PostDto> findPostList(Pageable pageable);

    List<PostDto> findAllDesc();


    Page<PostDto> findByCategoryId(Long categoryId, Pageable pageable);

    Page<PostDto> findByMemberId(Long memberId, Pageable pageable);

    Post showDetailPost(Long id);



}
