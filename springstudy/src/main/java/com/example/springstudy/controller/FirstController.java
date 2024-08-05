package com.example.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // 컨트롤러 선언
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou() {
        return "greetings";  // greetings.mustache 파일 반환
    }
}
