package com.example.demo.service.impl;


import com.example.readingisgood.dto.order.OrderDto;
import com.example.readingisgood.dto.order.OrderItemRequestDto;
import com.example.readingisgood.dto.order.OrderRequestDto;
import com.example.readingisgood.handler.FriendlyException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.service.AuthService;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.service.impl.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    AuthService authService;

    @Mock
    BookService bookService;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    public void save() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        OrderItemRequestDto orderItemRequestDto = new OrderItemRequestDto();
        orderItemRequestDto.setCount((short) 1);
        orderItemRequestDto.setBookid("2");
        orderRequestDto.setItems(Arrays.asList(orderItemRequestDto));

        Book book = new Book();
        book.setStock((short) 2);
        book.setName("TestName");
        book.setAuthor("TestAuthor");
        book.setPrice(12.0F);
        when(bookService.findById(anyString())).thenReturn(book);

        when(orderRepository.save(any())).thenReturn(new Order());

        OrderDto result = orderService.save(orderRequestDto);
        Assert.assertEquals(result.getId(), null);
    }


    @Test(expected = FriendlyException.class)
    public void save_with_invalid_stock() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        OrderItemRequestDto orderItemRequestDto = new OrderItemRequestDto();
        orderItemRequestDto.setCount((short) 1);
        orderItemRequestDto.setBookid("2");
        orderRequestDto.setItems(Arrays.asList(orderItemRequestDto));

        Book book = new Book();
        book.setStock((short) 0);
        book.setName("TestName");
        book.setAuthor("TestAuthor");
        book.setPrice(12.0F);
        when(bookService.findById(anyString())).thenReturn(book);

        orderService.save(orderRequestDto);
    }

    @Test
    public void findAllOrderByCustomerWithPageable() {
        when(orderRepository.findAllByCreatedByUser(anyString(), any())).thenReturn(Arrays.asList());
        List<OrderDto> result = orderService.findAllOrderByCustomerWithPageable(1,2, new Customer());
        Assert.assertEquals(result.size(), Arrays.asList().size());
    }

    @Test
    public void get() {
        Order order = new Order();
        OrderDto orderDto = new OrderDto(order);
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));
        OrderDto result = orderService.get("1");
        Assert.assertEquals(result.getId(), orderDto.getId());
    }

    @Test(expected = FriendlyException.class)
    public void get_with_empty() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.empty());
        orderService.get("1");
    }

    @Test
    public void getAll() {
        String startDate = "12.12.2000";
        String endDate = "12.12.2021";

        Customer customer = new Customer();
        when(authService.credential()).thenReturn(customer);
        when(orderRepository.findAllByCreatedByUserAndDateBetween(anyString(), any(), any())).thenReturn(Arrays.asList());
        List<OrderDto> result = orderService.getAll(startDate, endDate);
        Assert.assertEquals(result.size(), Arrays.asList().size());

    }
}