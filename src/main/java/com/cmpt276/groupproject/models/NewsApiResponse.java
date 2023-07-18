package com.cmpt276.groupproject.models;

import java.util.List;

public class NewsApiResponse {

    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
