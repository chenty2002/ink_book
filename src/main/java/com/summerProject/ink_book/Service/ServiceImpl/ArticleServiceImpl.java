package com.summerProject.ink_book.Service.ServiceImpl;

import com.summerProject.ink_book.Entity.Article;
import com.summerProject.ink_book.Mapper.ArticleMapper;
import com.summerProject.ink_book.Service.ArticleService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public Result<String> saveArticle(Article article) {
        if (articleMapper.insertArticle(article) > 0) {
            return Result.success("Publish success", "");
        } else return Result.fail("Publish failed");
    }

    @Override
    public Result<Article> getArticleById(int id) {
        Article article = articleMapper.selectArticleById(id);
        return Result.success("Article Info", article);
    }

    @Override
    public Result<String> deleteArticleById(Integer id) {
        if (articleMapper.deleteArticleById(id) > 0) {
            return Result.success("Article Deleted", "");
        } else return Result.fail("Article not Deleted");
    }

    @Override
    public Result<List<Article>> getUserArticle(Integer id) {
        List<Article> articles = articleMapper.selectArticleByUser(id);
        return Result.success("All Articles of a user", articles);
    }

    @Override
    public Result<List<Article>> getGroupArticle(Integer id) {
        List<Article> articles = articleMapper.selectArticleByGroup(id);
        return Result.success("All Articles of a group", articles);
    }

    @Override
    public Result<String> updateArticle(Article article) {
        if (articleMapper.updateArticle(article) > 0)
            return Result.success("Article Modified", "");
        else return Result.fail("Article not Modified");
    }


}


