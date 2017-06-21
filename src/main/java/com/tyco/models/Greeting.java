package com.tyco.models;

/**
 * Created by jamessunthonlap on 6/21/17.
 */
public class Greeting {
    private final long id;
    private final String content;
    private final int limit;

    public Greeting(long id, String content, int limit) {
        this.id = id;
        this.content = content;
        this.limit = limit;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getLimit() {
        return limit;
    }

}
