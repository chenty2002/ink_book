package com.summerProject.ink_book.Controller;

import com.alibaba.fastjson.JSONObject;
import com.summerProject.ink_book.Entity.Article;
import com.summerProject.ink_book.Service.ServiceImpl.ArticleServiceImpl;
import com.summerProject.ink_book.Utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleServiceImpl articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    // 保存文章 POST url?传用户id 请求体传文章
    /*
        url: /save?id=
        请求体参数
        {
            "title": ,
            "content":
        }
     */
    @PostMapping("/save")
    public Result<Article> saveArticle(@RequestParam("id") Integer id, @RequestBody JSONObject object) {
        log.info("[ArticleController.saveArticle] --- requesting creating an article");
        Article article = new Article();
        article.setTitle(object.getString("title"));
        article.setAuthor(id);
        article.setContent(object.getString("content"));
        return articleService.saveArticle(article);
    }

    // 获取文章信息 GET url?传参
    /*
        url: /info?id=
     */
    @GetMapping("/info")
    public Result<Article> getArticleInfo(@RequestParam("id") Integer id) {
        log.info("[ArticleController.getArticleInfo] --- requesting article info");
        return articleService.getArticleById(id);
    }

    // 删除文章 DELETE url?传参
    /*
        url: /delete?id=
     */
    @DeleteMapping("/delete")
    public Result<String> deleteArticle(@RequestParam("id") Integer id) {
        log.info("[ArticleController.deleteArticle] --- requesting deleting an article");
        return articleService.deleteArticleById(id);
    }

    // 获取该用户的所有文章 GET url?传参
    /*
        url: /userAll?userId=
     */
    @GetMapping("/userAll")
    public Result<List<Article>> getAllUserArticle(@RequestParam("userId") Integer id) {
        log.info("[ArticleController.getAllUserArticle] --- requesting all articles of a User");
        return articleService.getUserArticle(id);
    }

    // 获取团队的所有文章 GET url?传参
    /*
        url: /groupAll?groupId=
     */
    @GetMapping("/groupAll")
    public Result<List<Article>> getAllGroupArticle(@RequestParam("groupId") Integer id) {
        log.info("[ArticleController.getAllGroupArticle] --- requesting all articles of a group");
        return articleService.getGroupArticle(id);
    }

    // 修改文章 POST 请求体传参
    /*
        url: /update
        请求体参数
        {
            "articleId": ,
            "title": , (可选)
            "content": (可选)
        }
     */
    @PostMapping("/update")
    public Result<String> updateArticle(@RequestBody JSONObject object) {
        log.info("[ArticleController.updateArticle] --- requesting updating an article by User");
        Article article = new Article();
        article.setArticleId(object.getInteger("articleId"));
        article.setTitle(object.getString("title"));
        article.setContent(object.getString("content"));
        return articleService.updateArticle(article);
    }
}