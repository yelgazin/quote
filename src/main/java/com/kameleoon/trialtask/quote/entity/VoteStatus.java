package com.kameleoon.trialtask.quote.entity;

public enum VoteStatus {

    LIKE(1),
    DISLIKE(-1);

    private final int code;

    VoteStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static VoteStatus fromCode(int code) {
        if (code == LIKE.getCode()) {
            return LIKE;
        } else if (code == DISLIKE.getCode()) {
            return DISLIKE;
        } else {
            throw new IllegalArgumentException("Code [" + code + "] not supported");
        }
    }
}

