package com.example.readingisgood.dto.order;

import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.model.OrderItem;

public class OrderItemDto {

    private String id;
    private BookDto book;
    private Float price;
    private short count;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.book = new BookDto(orderItem.getBook());
        this.count = orderItem.getCount();
        this.price = orderItem.getPrice();
        this.count = orderItem.getCount();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
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
