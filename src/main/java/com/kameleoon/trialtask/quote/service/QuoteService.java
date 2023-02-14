package com.kameleoon.trialtask.quote.service;

import com.kameleoon.trialtask.quote.entity.Quote;

import java.util.List;

public interface QuoteService {

    List<Quote> getQuotes();

    Quote getQuote(int id);

    Quote createQuote(String userName, Quote quote);

    void deleteQuote(String userName, int quoteId);

    Quote updateQuote(String userName, Quote quote);

    void addLikeToQuote(String userName, int quoteId);

    void removeLikeFromQuote(String userName, int quoteId);

    void addDislikeToQuote(String userName, int quoteId);

    void removeDislikeFromQuote(String userName, int quoteId);

    List<Quote> getTopQuotes(int top);

    List<Quote> getWorseQuotes(int worse);

    Quote getRandomQuote();
}
