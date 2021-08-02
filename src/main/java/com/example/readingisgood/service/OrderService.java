package com.example.readingisgood.service;

import com.example.readingisgood.dto.order.OrderDto;
import com.example.readingisgood.dto.order.OrderRequestDto;
import com.example.readingisgood.model.Customer;

import java.util.List;

public interface OrderService {

    OrderDto save(OrderRequestDto orderRequestDto);

    List<OrderDto> findAllOrderByCustomerWithPageable(Integer page, Integer size, Customer customer);

    OrderDto get(String id);

    List<OrderDto> getAll(String startDate, String endDate);
}
