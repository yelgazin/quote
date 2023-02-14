package com.kameleoon.trialtask.quote.controller;

import com.kameleoon.trialtask.quote.entity.Quote;
import com.kameleoon.trialtask.quote.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quotes")
    public List<Quote> getQuotes() {
        return quoteService.getQuotes();
    }

    @GetMapping("/quotes/{id}")
    public Quote getQuote(@PathVariable int id) {
        return quoteService.getQuote(id);
    }

    @GetMapping("/quotes/random")
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @GetMapping(value = "/quotes", params = {"top"})
    public List<Quote> getTopQuotes(int top) {
        return quoteService.getTopQuotes(top);
    }

    @GetMapping(value = "/quotes", params = {"worse"})
    public List<Quote> getWorseQuotes(int worse) {
        return quoteService.getWorseQuotes(worse);
    }
}
