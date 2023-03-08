package com.example.waffleeungaebackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @PostMapping("/test")
    public String test(){
        return "<h1> test oooo</h1>";
    }
}
