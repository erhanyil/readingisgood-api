package com.example.readingisgood.model;

import com.example.readingisgood.util.CoreUtil;

public class OrderItem  {

    private String id = CoreUtil.generateID();

    private Book book;

    private Float price;

    private short count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }

}
