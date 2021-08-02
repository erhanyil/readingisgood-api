package com.example.readingisgood.service.impl;

import com.example.readingisgood.constant.GenericError;
import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.dto.order.OrderDto;
import com.example.readingisgood.dto.order.OrderItemRequestDto;
import com.example.readingisgood.dto.order.OrderRequestDto;
import com.example.readingisgood.handler.FriendlyException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.model.OrderItem;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.service.AuthService;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.service.OrderService;
import com.example.readingisgood.util.DateUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final AuthService authService;

    public OrderServiceImpl(OrderRepository orderRepository, BookService bookService, AuthService authService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
        this.authService = authService;
    }

    @Override
    public OrderDto save(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setDate(new Date());

        List<OrderItem> orderItems = new ArrayList<>();
        Float totalAmount = 0F;

        for (OrderItemRequestDto orderItemRequest : orderRequestDto.getItems()) {
            OrderItem orderItem = new OrderItem();
            Book book = bookService.findById(orderItemRequest.getBookid());
            if(orderItemRequest.getCount() > book.getStock()) {
                throw new FriendlyException(GenericError.INVALID_STOCK);
            }
            book.setStock((short) (book.getStock() - orderItemRequest.getCount()));
            bookService.update(book.getId(), new BookDto(book));
            orderItem.setBook(book);
            totalAmount += book.getPrice() * orderItemRequest.getCount();
            orderItem.setCount(orderItemRequest.getCount());
            orderItem.setPrice(book.getPrice());
            orderItems.add(orderItem);

        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
        return new OrderDto(order);
    }

    @Override
    public List<OrderDto> findAllOrderByCustomerWithPageable(Integer page, Integer size, Customer customer) {
        return orderRepository.findAllByCreatedByUser(customer.getId(), PageRequest.of(page, size)).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @Override
    public OrderDto get(String id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return new OrderDto(order);
        }
        throw new FriendlyException(GenericError.RECORD_NOT_FOUND);
    }

    @Override
    public List<OrderDto> getAll(String startDate, String endDate) {
        Customer customer = authService.credential();
        Date start = DateUtil.convert(startDate);
        Date end = DateUtil.convert(endDate);
        return orderRepository.findAllByCreatedByUserAndDateBetween(customer.getId(), start, end).stream().map(OrderDto::new).collect(Collectors.toList());
    }


}
