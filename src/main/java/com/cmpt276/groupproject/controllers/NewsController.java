package com.cmpt276.groupproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cmpt276.groupproject.models.Article;
import com.cmpt276.groupproject.models.NewsApiResponse;
import com.cmpt276.groupproject.service.NewsService;

@RestController
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public ModelAndView getNews() {
            ModelAndView modelAndView = new ModelAndView("news");
            modelAndView.addObject("articles", newsService.getNews("ca", "business").getArticles());
        return modelAndView;
    }
}
