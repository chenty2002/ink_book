package com.summerProject.ink_book.Entity;


public class Article {
    private int articleId;
    private String title;
    private Integer author;
    private String content;

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}

