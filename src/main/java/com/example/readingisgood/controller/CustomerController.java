package com.example.readingisgood.controller;

import com.example.readingisgood.dto.customer.CustomerDto;
import com.example.readingisgood.dto.customer.CustomerRequestDto;
import com.example.readingisgood.dto.order.OrderDto;
import com.example.readingisgood.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.create(customerRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderDto> order(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10", required = false) Integer size) {
        return customerService.orders(page, size);
    }
}
