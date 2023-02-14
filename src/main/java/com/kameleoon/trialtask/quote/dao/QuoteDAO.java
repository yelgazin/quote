package com.kameleoon.trialtask.quote.dao;

import com.kameleoon.trialtask.quote.entity.Quote;

import java.util.List;

public interface QuoteDAO {
    List<Quote> getQuotes();
    Quote getQuote(int id);
    Quote createQuote(Quote quote);
    void deleteQuote(int id);
    void updateQuote(Quote quote);
    List<Quote> getTopQuotes(int top);
    List<Quote> getWorseQuotes(int worse);
    Quote getRandomQuote();
}
