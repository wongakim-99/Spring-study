package com.wongakim.springstudy.api.article;

import com.wongakim.springstudy.api.article.dto.request.ArticleRequest;
import com.wongakim.springstudy.api.article.dto.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleApiController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponse> index() {
        return articleService.index();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> show(@PathVariable Long id) {
        ArticleResponse response = articleService.show(id);
        return (response != null) ?
                ResponseEntity.ok(response) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> create(@RequestBody ArticleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.create(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArticleResponse> update(@PathVariable Long id, @RequestBody ArticleRequest request) {
        ArticleResponse response = articleService.update(id, request);
        return (response != null) ?
                ResponseEntity.ok(response) :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ArticleResponse deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/transaction-test")
    public ResponseEntity<List<ArticleResponse>> transactionTest(@RequestBody List<ArticleRequest> requests) {
        List<ArticleResponse> createdList = articleService.createArticles(requests);
        return (createdList != null) ?
                ResponseEntity.ok(createdList) :
                ResponseEntity.badRequest().build();
    }
}
