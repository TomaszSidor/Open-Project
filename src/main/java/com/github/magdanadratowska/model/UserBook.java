package com.github.magdanadratowska.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserBook {
    private Book book;
    private LocalDate additionDate;
    private int rate;
    private boolean isActive;
    private boolean isOwned;

    public UserBook() {
    }

    public UserBook(Book book, LocalDate additionDate, int rate) {
        this.book = book;
        this.additionDate = additionDate;
        this.rate = rate;
        this.isActive =true;
    }

    public UserBook(Book book, boolean isActive, boolean isOwned) {
        this.book = book;
        this.isActive = isActive;
        this.isOwned = isOwned;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(LocalDate additionDate) {
        this.additionDate = additionDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public boolean getIsOwned() {
        return isOwned;
    }

    public void setIsOwned(boolean owned) {
        isOwned = owned;
    }
}
