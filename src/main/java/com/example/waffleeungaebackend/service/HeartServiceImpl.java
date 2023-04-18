package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.entity.HeartPost;
import com.example.waffleeungaebackend.entity.Member;
import com.example.waffleeungaebackend.entity.Post;
import com.example.waffleeungaebackend.repository.HeartRepository;
import com.example.waffleeungaebackend.repository.MemberRepository;
import com.example.waffleeungaebackend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class HeartServiceImpl implements HeartService {
    private final HeartRepository heartRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    @Override
    public String likeBoard(Long id,Long member_id) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
        log.info("1: {}",post);
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findById(member_id).orElseThrow(()->new IllegalArgumentException("멤버가 없습니다."));
        log.info("2: {}",member);
        if(heartRepository.findByPostAndMember(post, member) == null) {
            // 좋아요를 누른적 없다면 LikeBoard 생성 후, 좋아요 처리
            if(post.getLikeCount() == null){
                post.setLikeCount(0L);
            }
            post.setLikeCount(post.getLikeCount() + 1);
            log.info("3,4: {}",post.getLikeCount());
            HeartPost heartPost = new HeartPost(post, member); // true 처리
            postRepository.save(post);
            heartRepository.save(heartPost);
            return "좋아요 처리 완료";
        } else {
            HeartPost heartPost = heartRepository.findByPostAndMember(post, member);
            post.setLikeCount(post.getLikeCount() - 1);
            postRepository.save(post);
            heartRepository.delete(heartPost);
            return "좋아요 취소";
        }
    }
}