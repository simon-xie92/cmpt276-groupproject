package com.cmpt276.groupproject.controllers;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cmpt276.groupproject.models.CandleStickData;
import com.cmpt276.groupproject.models.quote;
import com.cmpt276.groupproject.models.quoteResponse;
import com.cmpt276.groupproject.service.FinnhubService;

@RestController
public class FinnhubController {

    private final FinnhubService finnhubService;

    public FinnhubController(FinnhubService finnhubService) {
        this.finnhubService = finnhubService;
    }

    @GetMapping("/quote")
    public quote getQuote(@RequestParam String symbol) {
        return finnhubService.getQuote(symbol);
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