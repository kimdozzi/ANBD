package com.ll.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    // Slf4j 어노테이션으로 대신
    // Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/")
    public String home() {
        log.info("home controller !!");
        return "home";
    }
}
