package com.summerProject.ink_book.Controller;

import com.summerProject.ink_book.Entity.Article;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.ServiceImpl.ArticleServiceImpl;
import com.summerProject.ink_book.Utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleServiceImpl articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    // 保存文章 POST 请求体传参
    @PostMapping("/save")
    public Result<String> saveArticle(HttpServletRequest request, @RequestBody Article article) {
        log.info("[ArticleController.saveArticle] --- requesting creating an article");
//        Integer id = ((User) request.getSession().getAttribute("curUser")).getUserId();
//        article.setAuthor(id);
        return articleService.saveArticle(article);
    }

    // 获取文章信息 GET url?传参
    @GetMapping("/info/{id}")
    public Result<Article> getArticleInfo(@PathVariable("id") Integer id) {
        log.info("[ArticleController.getArticleInfo] --- requesting article info");
        return articleService.getArticleById(id);
    }

    // 删除文章 DELETE url?传参
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteArticle(@PathVariable("id") int id) {
        log.info("[ArticleController.deleteArticle] --- requesting deleting an article");
        return articleService.deleteArticleById(id);
    }

    // 获取该用户的所有文章 GET 无参数
    @GetMapping("/all")
    public Result<List<Article>> getAllUserArticle(HttpServletRequest request) {
        log.info("[ArticleController.getAllUserArticle] --- requesting all articles of a User:");
        User user = (User) request.getSession().getAttribute("curUser");
        log.info(user.toString());
        return articleService.getUserArticle(user.getUserId());
    }

    // 获取团队的所有文章 GET url?传参
    @GetMapping("/all/{groupId}")
    public Result<List<Article>> getAllGroupArticle(@PathVariable("groupId") Integer id) {
        log.info("[ArticleController.getAllGroupArticle] --- requesting all articles of a group");
        return articleService.getGroupArticle(id);
    }

    // 修改文章 POST 请求体传参
    @PostMapping("/update")
    public Result<String> updateArticle(HttpServletRequest request, @RequestBody Article article) {
        log.info("[ArticleController.updateArticle] --- requesting updating an article by User:");
//        User user = (User) request.getSession().getAttribute("curUser");
//        log.info(user.toString());
        return articleService.updateArticle(article);
    }
}