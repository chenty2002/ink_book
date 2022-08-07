package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Entity.Article;
import com.summerProject.ink_book.Utils.Result;

import java.util.List;

public interface ArticleService {
    Result<Article> saveArticle(Article article);

    Result<Article> getArticleById(int id);

    Result<String> deleteArticleById(Integer id);

    Result<List<Article>> getUserArticle(Integer id);

    Result<List<Article>> getGroupArticle(Integer id);

    Result<String> updateArticle(Article article);
}
