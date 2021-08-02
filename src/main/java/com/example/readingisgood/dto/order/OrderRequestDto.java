package com.example.readingisgood.dto.order;

import javax.validation.constraints.Size;
import java.util.List;

public class OrderRequestDto {

    @Size(min = 1, max = 10, message = "At least 1 item and maximum 10 item must be exist")
    private List<OrderItemRequestDto> items;

    public List<OrderItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDto> items) {
        this.items = items;
    }
}
