package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.dto.PostDto;
import com.example.waffleeungaebackend.dto.request.PostCreateRequestDto;
import com.example.waffleeungaebackend.dto.request.PostPatchRequestDto;
import com.example.waffleeungaebackend.entity.Category;
import com.example.waffleeungaebackend.entity.Post;
import com.example.waffleeungaebackend.repository.CategoryRepository;
import com.example.waffleeungaebackend.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;


    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(new Post());
    }


    @Override
    public Post addPostList(PostCreateRequestDto postCreateRequestDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("TODO 생성실패"));
        return postRepository.save(postCreateRequestDto.toEntity(category));
    }

    @Override
    public Post updateById(Long id, PostPatchRequestDto postPatchRequestDto){
        Post post = this.findById(id);
        Category category = categoryRepository.findById(postPatchRequestDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("TODO 생성실패"));

        post.setCategory(category);
        post.setContent(postPatchRequestDto.getContent());

        return this.postRepository.save(post);
    }

    @Override
    public void deletePostList(Long id) {
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
}
