package com.cmpt276.groupproject.controllers;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cmpt276.groupproject.models.CandleStickData;
import com.cmpt276.groupproject.models.Earning;
import com.cmpt276.groupproject.models.Metric;
import com.cmpt276.groupproject.models.News;
import com.cmpt276.groupproject.models.RecommendationTrend;
import com.cmpt276.groupproject.models.quote;
import com.cmpt276.groupproject.models.quoteResponse;
import com.cmpt276.groupproject.service.FinnhubService;

import jakarta.websocket.server.PathParam;

@RestController
public class FinnhubController {

    private final FinnhubService finnhubService;

    public FinnhubController(FinnhubService finnhubService) {
        this.finnhubService = finnhubService;
    }

    @GetMapping("/stocks")
    public ModelAndView getStockData(@RequestParam (defaultValue= "aapl") String symbol)
    {
        RecommendationTrend[] recommendations = finnhubService.getRecommendationTrends(symbol);
        quote quoteresponse = finnhubService.getQuote(symbol);
        News[] news = finnhubService.getNews(symbol, "2023-01-01", "2023-12-31");



        ModelAndView modelAndView = new ModelAndView("stocks");

        modelAndView.addObject("quote", quoteresponse);
        modelAndView.addObject("recommendations", recommendations);
        modelAndView.addObject("news", news);

        return modelAndView;


    }

    @GetMapping(value="/quoteTest")
    public quote getquote(@RequestParam String symbol) {
        return finnhubService.getQuote(symbol);
    }

    @GetMapping ("/recommendationsTest")
    public RecommendationTrend[] getRecommendationTrends(@RequestParam String symbol)
    {
        return finnhubService.getRecommendationTrends(symbol);
    }

    @GetMapping ("/earningsTest")
    public Earning[] getearnings(@RequestParam String symbol)
    {
        return finnhubService.getEarnings(symbol);
    }

    @GetMapping ("/newsTest")
    public News[] getnews(@RequestParam String symbol)
    {
        return finnhubService.getNews(symbol, "2023-01-01", "2023-12-31");
    }
    

    @GetMapping("/candle")

        public ModelAndView getCandleStickData (
            @RequestParam String symbol, 
    @RequestParam(defaultValue = "60") int resolution, 
    @RequestParam(defaultValue = "0") long from, 
    @RequestParam(defaultValue = "0") long to ) {

        if (from == 0) {
            from = Instant.now().getEpochSecond() - (864000 * 30);
        }
        if (to == 0) {
            to = Instant.now().getEpochSecond();
        }

        ModelAndView modelandview = new ModelAndView("tempcandle");

        CandleStickData candleStickData = finnhubService.getCandleData(symbol, resolution, from, to);

        modelandview.addObject("candleData", candleStickData);

        return modelandview;
    }

        
           

}