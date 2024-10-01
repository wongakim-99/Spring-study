package com.example.springstudy.api;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import com.example.springstudy.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")  // 전체 게시글 조회
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")  // 단일 게시글 조회
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);  // 서비스를 통해 게시글 수정
        return (updated != null) ?  // 수정되면 정상, 안 되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 1. DB에서 대상 엔티티가 있는지 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 2. 대상 엔티티가 없어서 요청 자체가 잘못됐을 경우 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//        // 3. 대상 엔티티가 있으면 삭제하고 정상 응답(200)반환
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }
}
