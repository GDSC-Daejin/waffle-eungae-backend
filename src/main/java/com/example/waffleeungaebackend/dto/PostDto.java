package com.example.waffleeungaebackend.dto;

import com.example.waffleeungaebackend.entity.Category;
import com.example.waffleeungaebackend.entity.LikePost;
import com.example.waffleeungaebackend.entity.Member;
import com.example.waffleeungaebackend.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Category category;
    private Member member;
    private LikePost likePost;

    public PostDto(Post post){
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createDate = post.getCreateDate();
        this.category = post.getCategory();
        this.member = post.getMember();
        this.likePost = post.getLikePost();
    }
}
