package com.example.readingisgood.dto.order;

import com.example.readingisgood.model.Order;
import com.example.readingisgood.model.OrderItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private String id;
    private List<OrderItemDto> items;
    private Float amount;
    private Date date;
    private String state;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.items = new ArrayList<>();
        for (OrderItem orderItem : order.getItems()) {
            this.items.add(new OrderItemDto(orderItem));
        }
        this.amount = order.getTotalAmount();
        this.date = order.getDate();
        this.state = order.getState();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
