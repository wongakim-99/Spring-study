package com.example.springstudy.controller;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import com.example.springstudy.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller  // 컨트롤러 선언
public class ArticleController {  // URL 요청 접수
    @Autowired  // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입 (DI)
    private ArticleRepository articleRepository;  // articleRepository 객체 선언

    @GetMapping("/articles/new")  // 메서드 생성 및 반환값 작성
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {  // 폼 데이터를 DTO로 받기
        System.out.println(form.toString());  // DTO에 폼 데이터가 잘 담겼는지 확인
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());  // DTO가 엔티티로 잘 변환되는지 확인 출력
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);  // article 엔티티를 저장해 saved 객체에 반환
        System.out.println(saved.toString());  // article 이 DB에 잘 저장되는지 확인 출력
        return "";
    }
}
