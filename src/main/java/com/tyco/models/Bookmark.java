package com.tyco.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by jamessunthonlap on 6/22/17.
 */
@Entity
public class Bookmark {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Account account;

    private String uri;
    private String description;

    public Bookmark() {
    }

    public Bookmark(Account account, String uri, String description) {
        this.account = account;
        this.uri = uri;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
