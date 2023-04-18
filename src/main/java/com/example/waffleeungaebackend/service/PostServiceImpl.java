package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.dto.PostDto;
import com.example.waffleeungaebackend.dto.request.PostCreateRequestDto;
import com.example.waffleeungaebackend.dto.request.PostPatchRequestDto;
import com.example.waffleeungaebackend.entity.Category;
import com.example.waffleeungaebackend.entity.Member;
import com.example.waffleeungaebackend.entity.Post;
import com.example.waffleeungaebackend.repository.CategoryRepository;
import com.example.waffleeungaebackend.repository.MemberRepository;
import com.example.waffleeungaebackend.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    private final MemberRepository memberRepository;


    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(new Post());
    }


    @Override
    public Post addPostList(PostCreateRequestDto postCreateRequestDto, Long categoryId, Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("유저정보가 없습니다."));

        if(member.getLevel() == null){
            member.setLevel(0L);
        }
        member.setLevel(member.getLevel() + 100);
        memberRepository.save(member);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("TODO 생성실패"));
        return postRepository.save(postCreateRequestDto.toEntity(category, member));
    }

    @Override
    public Post updateById(Long id, PostPatchRequestDto postPatchRequestDto, Long memberID){
        Member member = memberRepository.findById(memberID).orElseThrow(() -> new IllegalArgumentException("유저정보가 없습니다."));
        Post post = this.findById(id);
        Category category = categoryRepository.findById(postPatchRequestDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("TODO 생성실패"));

        if (!Objects.equals(post.getMember().getMemberId(), memberID)) {
            throw new IllegalStateException("해당 글을 삭제할 권한이 없습니다.");
        }

        post.setCategory(category);
        post.setContent(postPatchRequestDto.getContent());

        return this.postRepository.save(post);
    }

    @Override
    public void deletePostList(Long id, Long memberID) {
        Member member = memberRepository.findById(memberID).orElseThrow(() -> new IllegalArgumentException("유저정보가 없습니다."));
        Post post = this.findById(id);
        if (!Objects.equals(post.getMember().getMemberId(), memberID)) {
            throw new IllegalStateException("해당 글을 삭제할 권한이 없습니다.");
        }
        postRepository.deleteById(id);
    }

    @Override
    public Page<PostDto> findPostList(Pageable pageable) {
        Page<Post> page = postRepository.findAll(pageable);
        return page.map(Post::toDto);
    }

    @Override
    public List<PostDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
            .map(PostDto::new)
            .collect(Collectors.toList());
    }

    @Override
    public Page<PostDto> findByCategoryId(Long categoryId, Pageable pageable){
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("카테고리가 없습니다."));
        Page<Post> page = postRepository.findByCategory(category, pageable);
        return page.map(Post::toDto);
    }

    @Override
    public Page<PostDto> findByMemberId(Long memberId, Pageable pageable){
        Member member = this.memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("카테고리가 없습니다."));
        Page<Post> page = postRepository.findByMember(member, pageable);
        return page.map(Post::toDto);
    }

    @Override
    public Post showDetailPost(Long id){

        Post post = this.findById(id);

        post.setViewCount(post.getViewCount() + 1);

        this.postRepository.save(post);
        return postRepository.findById(id).orElse(null);
    }
}
