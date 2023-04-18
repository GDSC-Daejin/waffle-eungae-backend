package com.example.waffleeungaebackend.controller;

import com.example.waffleeungaebackend.config.login.CustomOAuth2MemberService;
import com.example.waffleeungaebackend.config.login.LoginUser;
import com.example.waffleeungaebackend.dto.MemberDto;
import com.example.waffleeungaebackend.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class MemberController {

    private final CustomOAuth2MemberService customOAuth2MemberService;

    @GetMapping("/loginId")
    public ResponseEntity<Long> getName(@ApiIgnore @LoginUser MemberDto memberDto){
        return ResponseEntity.ok(memberDto.getMemberId());
    }


    @GetMapping("/loginEmail")
    public ResponseEntity<String> getLoginEmail(@ApiIgnore @LoginUser MemberDto memberDto){
        return ResponseEntity.ok(memberDto.getEmail());
    }

    @GetMapping("loginLevel")
    public ResponseEntity<Long> getLoginLevel(@ApiIgnore @LoginUser MemberDto memberDto){
        return ResponseEntity.ok(memberDto.getLevel());
    }

    @GetMapping("loginName")
    public ResponseEntity<String> getLoginName(@ApiIgnore @LoginUser MemberDto memberDto){
        return ResponseEntity.ok(memberDto.getName());
    }

    @GetMapping("list")
    public ResponseEntity<List> getLoginMember(@ApiIgnore @LoginUser MemberDto memberDto){
        List<Object> list = new ArrayList<>();
        list.add(memberDto.getMemberId());
        list.add(memberDto.getEmail());
        list.add(memberDto.getName());
        list.add(memberDto.getLevel());
        return ResponseEntity.ok(list);
    }


    @GetMapping("Ranking")
    public ResponseEntity<Page<Member>> readMemberRanking(
            @PageableDefault(sort = "level", direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println("Member Ranking");

        Page<Member> member = this.customOAuth2MemberService.readAllMember(pageable);

        return ResponseEntity.ok(member);
    }
}
