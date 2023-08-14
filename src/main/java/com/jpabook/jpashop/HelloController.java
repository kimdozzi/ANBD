package com.jpabook.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


    // localhost:8080/주소이름
    @GetMapping("hello")
    // 컨트롤러에서 데이터를 실어서 뷰에 넘길 수 있다
    public String hello(Model model) {
        // 네임이 data라는 키의 값을 hello를 넘길 것이다.
        model.addAttribute("data","hello");

        // 화면이름
        return "hello";
    }
}
