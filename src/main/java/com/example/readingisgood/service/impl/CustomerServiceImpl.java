package com.example.readingisgood.service.impl;

import com.example.readingisgood.dto.customer.CustomerDto;
import com.example.readingisgood.dto.customer.CustomerRequestDto;
import com.example.readingisgood.dto.order.OrderDto;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.service.AuthService;
import com.example.readingisgood.service.CustomerService;
import com.example.readingisgood.service.OrderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    public CustomerServiceImpl(AuthService authService, PasswordEncoder passwordEncoder, CustomerRepository customerRepository, OrderService orderService) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.orderService = orderService;
    }

    @Override
    public CustomerDto create(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer(customerRequestDto);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        return new CustomerDto(customer);
    }

    @Override
    public List<OrderDto> orders(Integer page, Integer size) {
        Customer customer = this.authService.credential();
        return orderService.findAllOrderByCustomerWithPageable(page, size, customer);
    }
}
