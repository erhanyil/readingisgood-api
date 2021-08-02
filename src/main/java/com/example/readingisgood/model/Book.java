package com.example.readingisgood.model;

import com.example.readingisgood.dto.BookDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Document
public class Book extends Base {

    @Id
    private String id;

    //@Column(unique = true)
    @NotNull(message = "Name required")
    private String name;

    @NotNull(message = "Author required")
    private String author;

    @NotNull(message = "Price required")
    @Min(value = 1, message = "Price value must be minimum 1")
    private Float price;

    @NotNull(message = "Name required")
    @Min(value = 0, message = "Stock value must be minimum 0")
    private short stock;

    public Book() {

    }

    public Book(BookDto bookDto) {
        this.name = bookDto.getName();
        this.author = bookDto.getAuthor();
        this.price = bookDto.getPrice();
        this.stock = bookDto.getStock();
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
