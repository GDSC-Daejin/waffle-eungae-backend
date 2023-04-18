package com.example.waffleeungaebackend.dto.request;

import com.example.waffleeungaebackend.entity.Comment;
import com.example.waffleeungaebackend.entity.Member;
import com.example.waffleeungaebackend.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CommentRequestDto {

    private Long commentId;

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

    private LocalDateTime createDate;

    private Long postId;

    public Comment toEntity(Post post, Member member){
        return Comment.builder()
                .commentId(commentId)
                .content(content)
                .createDate(LocalDateTime.now())
                .post(post)
                .member(member)
                .build();
    }
}
