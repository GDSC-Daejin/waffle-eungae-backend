package com.example.waffleeungaebackend.controller;

import com.example.waffleeungaebackend.dto.SessionMember;
import com.example.waffleeungaebackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final PostService postsService;

    @GetMapping("/logintest")
    public String index(Model model, @LoginUser SessionMember member) {
        Pageable Pageable;
        model.addAttribute("posts", postsService.findAllDesc());
        if (member != null) {
            model.addAttribute("userName", member.getName());
        }
        return "index";
    }


}