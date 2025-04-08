package com.summerProject.ink_book.Mapper;

import com.summerProject.ink_book.Entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int insertArticle(Article article);

    Article selectArticleById(int id);

    int deleteArticleById(int id);

    List<Article> selectArticleByGroup(Integer id);

    int updateArticle(Article article);

    int deleteGroupArticle(Integer groupId);
}