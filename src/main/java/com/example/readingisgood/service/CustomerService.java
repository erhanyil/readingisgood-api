package com.example.readingisgood.service;

import com.example.readingisgood.dto.customer.CustomerDto;
import com.example.readingisgood.dto.customer.CustomerRequestDto;
import com.example.readingisgood.dto.order.OrderDto;

import java.util.List;

public interface CustomerService {

    CustomerDto create(CustomerRequestDto customer);

    List<OrderDto> orders(Integer page, Integer size);
}
