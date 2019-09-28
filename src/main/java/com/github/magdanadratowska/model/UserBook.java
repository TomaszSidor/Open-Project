package com.github.magdanadratowska.model;

public class UserBook {
    private Book book;
    private int rate;

    public UserBook() {
    }

    public UserBook(Book book, int rate) {
        this.book = book;
        this.rate = rate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
