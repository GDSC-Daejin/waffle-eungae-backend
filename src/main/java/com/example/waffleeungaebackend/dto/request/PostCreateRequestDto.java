package com.example.waffleeungaebackend.dto.request;

import com.example.waffleeungaebackend.entity.Category;
import com.example.waffleeungaebackend.entity.Member;
import com.example.waffleeungaebackend.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PostCreateRequestDto {

    @Schema(description = "제목입니다.", example = "제목")
    @NotEmpty(message="제목은 필수 항목입니다.")
    @Size(max=200)
    private String title;

    @Schema(description = "내용입니다.", example = "내용")
    @NotEmpty(message="내용은 필수 항목입니다.")
    private String content;

    @Nullable
    private MultipartFile file;

    @Nullable
    private String fileName;

    @Nullable
    private String filePath;

    @Nullable
    private Long viewCount;

    @Nullable
    private Long likeCount;


    public Post toEntity(Category category, Member member) {

        return Post.builder()
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .category(category)
                .fileName(fileName)
                .filePath(filePath)
                .viewCount(viewCount)
                .likeCount(likeCount)
                .member(member)
                .build();
    }
}
