package com.example.waffleeungaebackend.controller;

import com.example.waffleeungaebackend.config.login.LoginUser;
import com.example.waffleeungaebackend.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class test {

    @RequestMapping("/test")
    public String test() {
        log.info("test");
        return "<h1> test oooo</h1>";
    }

    @RequestMapping("/test2")
    public String test2(HttpServletRequest request, @LoginUser MemberDto user) {

        return "<h1> test oooo</h1>";
    }

}
