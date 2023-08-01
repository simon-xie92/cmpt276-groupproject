package com.cmpt276.groupproject.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cmpt276.groupproject.models.CandleStickData;
import com.cmpt276.groupproject.models.Earning;
import com.cmpt276.groupproject.models.Metric;
import com.cmpt276.groupproject.models.News;
import com.cmpt276.groupproject.models.RecommendationTrend;
import com.cmpt276.groupproject.models.quote;
import com.cmpt276.groupproject.models.quoteResponse;

@Service
public class FinnhubService {
    private static final String API_URL_quote = "https://finnhub.io/api/v1/quote?symbol=";
    private static final String API_KEY = "cj40579r01qlttl4ke2gcj40579r01qlttl4ke30";
    private static final String API_URL_candle = "https://finnhub.io/api/v1/stock/candle?symbol={symbol}&resolution={resolution}&from={from}&to={to}&token={token}";
    private static final String API_URL_trend = "https://finnhub.io/api/v1/stock/recommendation?symbol={symbol}&token={token}";
    private static final String EARNINGS_API_URL = "https://finnhub.io/api/v1/stock/earnings?symbol={symbol}&token={token}";
    private static final String NEWS_API_URL = "https://finnhub.io/api/v1/company-news?symbol={symbol}&from={from}&to={to}&token={token}";
    private static final String FINANCIALS_API_URL = "https://finnhub.io/api/v1/stock/metric?symbol={symbol}&metric=all&token={token}";


    public quote getQuote(String symbol) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.set("X-Finnhub-Token", API_KEY);
        HttpEntity<quote> entity = new HttpEntity<>(headers);
        ResponseEntity<quote> response = restTemplate.exchange(
                API_URL_quote + symbol, HttpMethod.GET, entity, quote.class);

        return response.getBody();
    }




    public CandleStickData getCandleData(String symbol, int resolution, long from, long to)
    {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CandleStickData> response = restTemplate.getForEntity(API_URL_candle, CandleStickData.class, symbol, resolution, from, to, API_KEY);
        return response.getBody();
    }


    public RecommendationTrend[] getRecommendationTrends(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RecommendationTrend[]> response = restTemplate.getForEntity(API_URL_trend, RecommendationTrend[].class, symbol, API_KEY);
        return response.getBody();
    }


    public Earning[] getEarnings(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Earning[]> response = restTemplate.getForEntity(EARNINGS_API_URL, Earning[].class, symbol, API_KEY);
        return response.getBody();
    }



    public News[] getNews(String symbol, String from, String to) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<News[]> response = restTemplate.getForEntity(NEWS_API_URL, News[].class, symbol, from, to, API_KEY);
        return response.getBody();
    }



    public Metric getFinancials(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Metric> response = restTemplate.getForEntity(FINANCIALS_API_URL, Metric.class, symbol, API_KEY);
        return response.getBody();
    }

}