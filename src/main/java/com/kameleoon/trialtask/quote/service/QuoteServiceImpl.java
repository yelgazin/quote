package com.kameleoon.trialtask.quote.service;

import com.kameleoon.trialtask.quote.dao.QuoteDAO;
import com.kameleoon.trialtask.quote.dao.UserDAO;
import com.kameleoon.trialtask.quote.entity.Quote;
import com.kameleoon.trialtask.quote.entity.User;
import com.kameleoon.trialtask.quote.entity.Vote;
import com.kameleoon.trialtask.quote.entity.VoteStatus;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteDAO quoteDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Quote> getQuotes() {
        return quoteDAO.getQuotes();
    }

    @Override
    public Quote getQuote(int id) {
        return quoteDAO.getQuote(id);
    }

    @Override
    public Quote createQuote(String userName, Quote quote) {
        User user = userDAO.getUserByName(userName);
        quote.setAuthor(user);
        return quoteDAO.createQuote(quote);
    }

    @Override
    public void deleteQuote(String userName, int quoteId) {
        quoteDAO.deleteQuote(quoteId);
    }

    @Override
    public Quote updateQuote(String userName, Quote quote) {
        Quote savedQuote = quoteDAO.getQuote(quote.getId());
        if (savedQuote != null) {
            savedQuote.setContent(quote.getContent());
            savedQuote.setUpdated(new Date());
            quoteDAO.updateQuote(savedQuote);
        } else {
            throw new ObjectNotFoundException(Quote.class.getName(), quote.getId());
        }
        return savedQuote;
    }

    private void addVote(String userName, int quoteId, VoteStatus status) {
        User user = userDAO.getUserByName(userName);
        Quote quote = quoteDAO.getQuote(quoteId);

        Vote vote = new Vote(status);
        vote.setAuthor(user);

        List<Vote> votes = quote.getVotes();
        votes.clear();
        votes.add(vote);
        quoteDAO.updateQuote(quote);
    }

    @Override
    public void addLikeToQuote(String userName, int quoteId) {
        addVote(userName, quoteId, VoteStatus.LIKE);
    }

    @Override
    public void removeLikeFromQuote(String userName, int quoteId) {
        removeVoteFromQuote(userName, quoteId);
    }

    @Override
    public void addDislikeToQuote(String userName, int quoteId) {
        addVote(userName, quoteId, VoteStatus.DISLIKE);
    }

    @Override
    public void removeDislikeFromQuote(String userName, int quoteId) {
        removeVoteFromQuote(userName, quoteId);
    }

    private void removeVoteFromQuote(String userName, int quoteId) {
        User user = userDAO.getUserByName(userName);
        Quote quote = quoteDAO.getQuote(quoteId);
        quote.getVotes().clear();
        quoteDAO.updateQuote(quote);
    }

    @Override
    public List<Quote> getTopQuotes(int top) {
        return quoteDAO.getTopQuotes(top);
    }

    @Override
    public List<Quote> getWorseQuotes(int worse) {
        return quoteDAO.getWorseQuotes(worse);
    }

    @Override
    public Quote getRandomQuote() {
        return quoteDAO.getRandomQuote();
    }
}
