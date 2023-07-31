package com.cmpt276.groupproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}