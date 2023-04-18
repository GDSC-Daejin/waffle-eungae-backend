package com.example.waffleeungaebackend.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
@Entity
public class HeartPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 좋아요 아이디
    private Long likePostId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    // 게시글 아이디
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_Id")
    // 유저 아이디
    private Member member;

    @Column(nullable = false)
    private boolean status; // true = 좋아요, false = 좋아요 취소



    public HeartPost(Post post, Member member) {
        this.post = post;
        this.member= member;
        this.status = true;
    }

    public void unLikeBoard(Post post) {
        this.status = false;
        post.setLikeCount(post.getLikeCount() - 1);
    }
}
