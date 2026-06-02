package com.wongakim.springstudy.api.article;

import com.wongakim.springstudy.api.article.dto.request.ArticleRequest;
import com.wongakim.springstudy.api.article.dto.response.ArticleResponse;
import com.wongakim.springstudy.schema.Article;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleResponse> index() {
        return articleRepository.findAll().stream()
                .map(ArticleResponse::from)
                .toList();
    }

    public ArticleResponse show(Long id) {
        return articleRepository.findById(id)
                .map(ArticleResponse::from)
                .orElse(null);
    }

    public ArticleResponse create(ArticleRequest request) {
        Article article = new Article(null, request.title(), request.content());
        return ArticleResponse.from(articleRepository.save(article));
    }

    public ArticleResponse update(Long id, ArticleRequest request) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            log.info("잘못된 요청! id: {}", id);
            return null;
        }
        target.patch(new Article(null, request.title(), request.content()));
        return ArticleResponse.from(articleRepository.save(target));
    }

    public ArticleResponse delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        ArticleResponse response = ArticleResponse.from(target);
        articleRepository.delete(target);
        return response;
    }

    @Transactional
    public List<ArticleResponse> createArticles(List<ArticleRequest> requests) {
        List<Article> saved = requests.stream()
                .map(r -> articleRepository.save(new Article(null, r.title(), r.content())))
                .toList();

        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));

        return saved.stream().map(ArticleResponse::from).toList();
    }
}
