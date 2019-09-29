package com.github.magdanadratowska.model;

import java.time.LocalDateTime;

public class UserBook {
    private Book book;
    private LocalDateTime additionDate;
    private int rate;
    private boolean isActive;

    public UserBook() {
    }

    public UserBook(Book book, LocalDateTime additionDate, int rate) {
        this.book = book;
        this.additionDate = additionDate;
        this.rate = rate;
        this.isActive =true;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
