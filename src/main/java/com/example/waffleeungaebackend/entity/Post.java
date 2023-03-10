package com.example.waffleeungaebackend.entity;

import com.example.waffleeungaebackend.dto.PostDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 게시글 아이디
    private Long id;

    // 게시글 제목
    private String title;

    // 게시글 내용
    private String content;

    // 작성일
    private LocalDateTime createDate;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer viewCount;

/*    @ManyToOne
    @JoinColumn(name = "memberId")
    // 작성자 아이디
    private Member member;*/

    @ManyToOne
    @JoinColumn
    // 카테고리 아이디
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Member member;

    @ManyToOne
    @JoinColumn
    private LikePost likePost;


    @Builder
    public Post(Long postId, String title, String content, LocalDateTime createDate, Category category, Member member, LikePost likePost){
        this.id = postId;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.category = category;
        this.member = member;
        this.likePost = likePost;
    }

    public PostDto toDto() {
        return PostDto.builder()
                .postId(id)
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .category(category)
                .member(member)
                .likePost(likePost)
                .build();
    }
}
