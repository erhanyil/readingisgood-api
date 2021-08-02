package com.example.readingisgood.controller;

import com.example.readingisgood.dto.order.OrderDto;
import com.example.readingisgood.dto.order.OrderRequestDto;
import com.example.readingisgood.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getAll(@RequestParam String startDate, @RequestParam String endDate) {
        return orderService.getAll(startDate, endDate);
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable String id) {
        return orderService.get(id);
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.save(orderRequestDto);
    }
}
