package com.example.waffleeungaebackend.repository;

import com.example.waffleeungaebackend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
        Member findByName(String username);
        Optional<Member> findByEmail(String email);
        Optional<Member> findById(Long aLong);
}
