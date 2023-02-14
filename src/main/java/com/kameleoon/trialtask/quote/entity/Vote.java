package com.kameleoon.trialtask.quote.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User author;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created = new Date();

    @Column(columnDefinition = "integer", nullable = false)
    private VoteStatus status;

    public Vote() {
    }

    public Vote(VoteStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public VoteStatus getStatus() {
        return status;
    }

    public void setStatus(VoteStatus status) {
        this.status = status;
    }

    public void setStatus(int code) {
        this.status = VoteStatus.fromCode(code);
    }

    public int toIntValue() {
        return status.getCode();
    }
}
