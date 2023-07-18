package com.cmpt276.groupproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cmpt276.groupproject.models.NewsApiResponse;

@Service
public class NewsService {
    private static final String API_KEY = "c4e09a14a7c94246a974b6d472ad4d95";
    private static final String API_URL = "https://newsapi.org/v2/top-headlines?country={country}&category={category}&apiKey=" + API_KEY;

    public NewsApiResponse getNews(String country, String category) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NewsApiResponse> response = restTemplate.getForEntity(API_URL, NewsApiResponse.class, country, category);
        return response.getBody();
    }
}