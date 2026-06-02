package com.wongakim.springstudy.api.article.dto.response;

import com.wongakim.springstudy.schema.Article;

public record ArticleResponse(Long id, String title, String content) {
    public static ArticleResponse from(Article article) {
        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
    }
}
