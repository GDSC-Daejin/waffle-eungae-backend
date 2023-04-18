package com.example.waffleeungaebackend.controller;

import com.example.waffleeungaebackend.config.login.LoginUser;
import com.example.waffleeungaebackend.dto.MemberDto;
import com.example.waffleeungaebackend.dto.PostDto;
import com.example.waffleeungaebackend.dto.request.PostCreateRequestDto;
import com.example.waffleeungaebackend.dto.request.PostPatchRequestDto;
import com.example.waffleeungaebackend.entity.Post;
import com.example.waffleeungaebackend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.util.UUID;


@AllArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping(value = "post/{categoryId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostDto> create(
            @ModelAttribute PostCreateRequestDto postCreateRequestDto,
            @PathVariable Long categoryId,
            @ApiIgnore @LoginUser MemberDto member) throws Exception{
        System.out.println("create");


        postCreateRequestDto.setLikeCount(0L);
        postCreateRequestDto.setViewCount(0L);

        MultipartFile file = postCreateRequestDto.getFile();

        if(file != null){
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + file.getOriginalFilename();

            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            postCreateRequestDto.setFileName(fileName);
            postCreateRequestDto.setFilePath("/files/" + fileName);
        }

        Post post = this.postService.addPostList(postCreateRequestDto, categoryId, member.getMemberId());
        return ResponseEntity.ok(new PostDto(post));
    }

    @PatchMapping("post/{id}")
    public ResponseEntity<PostDto> update(
            @PathVariable Long id,
            @RequestBody PostPatchRequestDto patchRequestDto,
            @ApiIgnore @LoginUser MemberDto member){
        System.out.println("update");

        Post post = postService.updateById(id, patchRequestDto, member.getMemberId());
        return ResponseEntity.ok(new PostDto(post));
    }

    @DeleteMapping("post/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @LoginUser MemberDto member){
        System.out.println("delete");

        this.postService.deletePostList(id, member.getMemberId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> readAll(@PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println("read all");

        Page<PostDto> postList = this.postService.findPostList(pageable);

        return ResponseEntity.ok(postList);
    }

    @GetMapping("/viewCount")
    public ResponseEntity<Page<PostDto>> readAllByViewCount(@PageableDefault(sort = "viewCount", direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println("read all");

        Page<PostDto> postList = this.postService.findPostList(pageable);

        return ResponseEntity.ok(postList);
    }

    @GetMapping("categoryPost/{categoryId}")
    public ResponseEntity<Page<PostDto>> readPostsByCategory(
            @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long categoryId){
        System.out.println("Posts by category");

        Page<PostDto> postsByCategoryList = this.postService.findByCategoryId(categoryId, pageable);

        return ResponseEntity.ok(postsByCategoryList);
    }

    @GetMapping("memberPost/{memberId}")
    public ResponseEntity<Page<PostDto>> readPostsByMember(
            @PageableDefault(sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long memberId){
        System.out.println("Posts by member");

        Page<PostDto> postsByMemberList = this.postService.findByMemberId(memberId, pageable);

        return ResponseEntity.ok(postsByMemberList);
    }

    //상세페이지
    @GetMapping("detail/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        Post post = postService.showDetailPost(id);
        return ResponseEntity.ok(new PostDto(post));
    }
}
