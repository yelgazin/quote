package com.kameleoon.trialtask.quote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition="TEXT")
    private String content;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User author;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "quote_id")
    @JsonIgnore
    private List<Vote> votes = new ArrayList<>();

    public Quote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public int getScore() {
        if (votes != null) {
            return votes.stream()
                    .map(Vote::toIntValue)
                    .mapToInt(Integer::intValue)
                    .sum();
        } else {
            return 0;
        }
    }

    public Map<Date, Integer> getScoreHistory() {
        return votes.stream().collect(
                Collectors.groupingBy(Vote::getCreated,
                Collectors.summingInt(Vote::toIntValue)));
    }
}
