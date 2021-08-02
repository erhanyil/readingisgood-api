package com.example.readingisgood.dto.order;

import javax.validation.constraints.NotNull;

public class OrderItemRequestDto {

    private String bookid;

    @NotNull(message = "At least 1 book must be added")
    private short count;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }
}
