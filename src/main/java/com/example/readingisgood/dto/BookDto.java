package com.example.readingisgood.dto;

import com.example.readingisgood.model.Book;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookDto {

    private String id;

    @NotNull(message = "Name required")
    private String name;

    @NotNull(message = "author required")
    private String author;

    @Min(value = 1, message = "Price must be at least 1")
    private Float price;

    @Min(value = 1, message = "Stock must be at least 1")
    private short stock;

    public BookDto() {
    }

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.stock = book.getStock();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public short getStock() {
        return stock;
    }

    public void setStock(short stock) {
        this.stock = stock;
    }
}
