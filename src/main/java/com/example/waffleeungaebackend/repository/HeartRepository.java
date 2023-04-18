package com.example.waffleeungaebackend.repository;

import com.example.waffleeungaebackend.entity.HeartPost;
import com.example.waffleeungaebackend.entity.Member;
import com.example.waffleeungaebackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HeartRepository extends JpaRepository<HeartPost, Integer> {
    HeartPost findByPostAndMember(Post post, Member member);

}
