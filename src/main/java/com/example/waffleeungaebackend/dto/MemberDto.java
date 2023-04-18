package com.example.waffleeungaebackend.dto;

import com.example.waffleeungaebackend.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MemberDto implements Serializable {
    private Long memberId;
    private String name;
    private String email;
    private Long level;

    public MemberDto(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.level = member.getLevel();
    }

    public Member getId() {
        return Member.builder().memberId(memberId).build();
    }
}
