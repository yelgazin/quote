package com.kameleoon.trialtask.quote.dao;

import com.kameleoon.trialtask.quote.entity.Quote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuoteDAOImpl implements QuoteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Quote> getQuotes() {
        return entityManager.createQuery("from Quote", Quote.class).getResultList();
    }

    @Override
    public Quote getQuote(int id) {
        return entityManager.find(Quote.class, id);
    }

    @Override
    public Quote createQuote(Quote quote) {
        entityManager.persist(quote);
        return quote;
    }

    @Override
    public void deleteQuote(int id) {
        Quote quote = entityManager.find(Quote.class, id);
        entityManager.remove(quote);
    }

    @Override
    public void updateQuote(Quote quote) {
        entityManager.persist(quote);
    }

    @Override
    public List<Quote> getTopQuotes(int top) {
        return entityManager.createQuery("select q from Quote q " +
                "left outer join q.votes v " +
                "group by q " +
                "order by coalesce(sum(v.status), 0) desc", Quote.class)
                .setMaxResults(top)
                .getResultList();
    }

    @Override
    public List<Quote> getWorseQuotes(int worse) {
        return entityManager.createQuery("select q from Quote q " +
                        "left outer join q.votes v " +
                        "group by q " +
                        "order by coalesce(sum(v.status), 0) asc", Quote.class)
                .setMaxResults(worse)
                .getResultList();
    }

    @Override
    public Quote getRandomQuote() {
        Number countNumber = entityManager.createQuery("select count(q.id) from Quote q", Number.class).getSingleResult();
        int count = countNumber.intValue();
        int rowNumber = 1 + (int) (Math.random() * count);

        Number idNumber = (Number) entityManager.createNativeQuery("" +
            "SELECT T1.ID " +
            "FROM (SELECT ROWNUM() AS NUM, ID AS ID FROM QUOTE) AS T1 " +
            "WHERE T1.NUM = :rowNumber", Number.class)
        .setParameter("rowNumber", rowNumber)
        .getSingleResult();

        return entityManager.find(Quote.class, idNumber.intValue());
    }
}
