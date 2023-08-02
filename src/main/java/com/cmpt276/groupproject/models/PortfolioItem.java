package com.cmpt276.groupproject.models;

public class PortfolioItem {
    private Stock stock;
    private quote quote;
    private RecommendationTrend[] recommendations;
    private News[] news;

    public PortfolioItem() {
    }

    public PortfolioItem(Stock stock, com.cmpt276.groupproject.models.quote quote,
            RecommendationTrend[] recommendations, News[] news) {
        this.stock = stock;
        this.quote = quote;
        this.recommendations = recommendations;
        this.news = news;

    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public quote getQuote() {
        return quote;
    }

    public void setQuote(quote quote) {
        this.quote = quote;
    }

    public RecommendationTrend[] getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(RecommendationTrend[] recommendations) {
        this.recommendations = recommendations;
    }

    public News[] getNews() {
        return news;
    }

    public void setNews(News[] news) {
        this.news = news;
    }

    
  

    
}
