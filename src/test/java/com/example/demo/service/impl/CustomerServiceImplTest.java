package com.example.demo.service.impl;

import com.example.readingisgood.dto.customer.CustomerDto;
import com.example.readingisgood.dto.customer.CustomerRequestDto;
import com.example.readingisgood.dto.order.OrderDto;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.service.AuthService;
import com.example.readingisgood.service.OrderService;
import com.example.readingisgood.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthService authService;

    @Mock
    OrderService orderService;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    public void create() {
        Customer customer = new Customer();
        CustomerDto customerDto = new CustomerDto(customer);
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        when(passwordEncoder.encode(anyString())).thenReturn("1");
        when(customerRepository.save(any())).thenReturn(customer);
        CustomerDto result = customerService.create(customerRequestDto);
        Assert.assertEquals(result.getId(), customerDto.getId());
    }

    @Test
    public void orders() {
        Customer customer = new Customer();
        when(authService.credential()).thenReturn(customer);
        when(orderService.findAllOrderByCustomerWithPageable(any(),any(), any())).thenReturn(Arrays.asList());
        List<OrderDto> result = customerService.orders(1,2);
        Assert.assertEquals(result, Arrays.asList());
    }
}