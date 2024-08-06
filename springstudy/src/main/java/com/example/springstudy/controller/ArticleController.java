package com.example.springstudy.controller;

import com.example.springstudy.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller  // 컨트롤러 선언
public class ArticleController {  // URL 요청 접수
    @GetMapping("/articles/new")  // 메서드 생성 및 반환값 작성
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {  // 폼 데이터를 DTO로 받기
        System.out.println(form.toString());  // DTO에 폼 데이터가 잘 담겼는지 확인
        return "";
    }
}
