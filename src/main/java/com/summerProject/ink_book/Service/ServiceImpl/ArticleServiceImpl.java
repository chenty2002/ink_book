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
    public Result<Article> saveArticle(Article article) {
        if (articleMapper.insertArticle(article) > 0) {
            return Result.success("Publish success", article);
        } else return Result.fail("Publish failed");
    }

    @Override
    public Result<Article> getArticleById(Integer id) {
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

    @Override
    public Result<String> deleteArticleByGroup(Integer groupId) {
        articleMapper.deleteGroupArticle(groupId);
        return Result.success("Group Article Deleted", "");
    }
}


