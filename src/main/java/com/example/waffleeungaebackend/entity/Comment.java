package com.example.waffleeungaebackend.entity;

import com.example.waffleeungaebackend.dto.CommentDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 댓글 아이디
    private Long commentId;

    // 댓글 내용
    private String content;

    // 좋아요
    //private Integer likes;

    // 작성일
    private LocalDateTime createDate;

/*    @ManyToOne
    @JoinColumn(name = "memberId")
    // 작성자 아이디
    private Member member;*/

    @ManyToOne
    @JoinColumn(name = "post_id")
    // 게시글 아이디
    private Post post;

    @ManyToOne
    @JoinColumn
    private Member member;

/*    @ManyToOne
    @JoinColumn
    // 부모 댓글
    private Comment comment;*/

    public CommentDto toDto(){
        return CommentDto.builder()
                .content(content)
                //.like(likes)
                .createDate(createDate)
                .postId(post.getId())
                .member(member)
                .build();
    }
}
