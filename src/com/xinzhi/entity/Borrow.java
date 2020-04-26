package com.xinzhi.entity;

public class Borrow {
    private  Integer id;
    private Book book;
    private User user;
    private String borrowtime;
    private String returntime;
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Borrow(Integer id, Book book, User user, String borrowtime, String returntime, Integer state) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.borrowtime = borrowtime;
        this.returntime = returntime;
        this.state = state;
    }
}
