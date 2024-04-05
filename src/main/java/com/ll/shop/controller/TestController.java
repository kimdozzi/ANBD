package com.ll.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    // 테스트
    @RequestMapping("/test")
    public String test() {
        return "This will return posts's URI.";
    }
}
