package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.entity.HeartPost;
import com.example.waffleeungaebackend.entity.Member;
import com.example.waffleeungaebackend.entity.Post;
import com.example.waffleeungaebackend.repository.HeartRepository;
import com.example.waffleeungaebackend.repository.MemberRepository;
import com.example.waffleeungaebackend.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HeartServiceImplTest {

    @Mock
    private HeartRepository heartRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private HeartServiceImpl heartService;

    @Test
    public void likeBoard_shouldLikePost_whenNotLikedBefore() {
        // Arrange
        Long postId = 1L;
        Long memberId = 2L;
        Post post = new Post();
        post.setId(postId);
        post.setLikeCount(0L);
        Member member = new Member();
        member.setId(memberId);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(heartRepository.findByPostAndMember(post, member)).thenReturn(null);

        // Act
        String result = heartService.likeBoard(postId, memberId);

        // Assert
        assertThat(result).isEqualTo("좋아요 처리 완료");
        assertThat(post.getLikeCount()).isEqualTo(1);
        verify(heartRepository).save(any(HeartPost.class));
    }

    @Test
    public void likeBoard_shouldUnlikePost_whenLikedBefore() {
        // Arrange
        Long postId = 1L;
        Long memberId = 2L;
        Post post = new Post();
        post.setId(postId);
        post.setLikeCount(1L);
        Member member = new Member();
        member.setId(memberId);

        HeartPost heartPost = new HeartPost(post, member);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(heartRepository.findByPostAndMember(post, member)).thenReturn(heartPost);

        // Act
        String result = heartService.likeBoard(postId, memberId);

        // Assert
        assertThat(result).isEqualTo("좋아요 취소");
        assertThat(post.getLikeCount()).isEqualTo(0);
        verify(heartRepository).delete(heartPost);
    }
}
