package org.solution.jdbc.models;

import java.util.Date;

public class Album {
    private Long id;
    private String name;
    private String author;
    private Long genre_id;
    private Date registerTime;

    public Album(Long id, String name, String author, Long genre_id, Date registerTime) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre_id = genre_id;
        this.registerTime = registerTime;
    }

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "Album " + id + " | name: " + name + " | author: " + author + " | date: " + registerTime + '\n';
    }
}
