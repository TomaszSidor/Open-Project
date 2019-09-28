package com.github.magdanadratowska.model;

import java.time.LocalDateTime;

public class UserBook {
    private Book book;
    private LocalDateTime additionDate;
    private int rate;

    public UserBook() {
    }

    public UserBook(Book book, LocalDateTime additionDate, int rate) {
        this.book = book;
        this.additionDate = additionDate;
        this.rate = rate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(LocalDateTime additionDate) {
        this.additionDate = additionDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
