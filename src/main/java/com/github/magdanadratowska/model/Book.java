package com.github.magdanadratowska.model;

public class Book {

    protected int id;
    protected String title;
    protected String authorName;
    protected String authorSurname;

    public Book(int id, String title, String authorName, String authorSurname) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public Book(String title, String authorName, String authorSurname) {
        this.title = title;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }
}
