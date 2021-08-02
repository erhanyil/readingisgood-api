package com.example.readingisgood.repository;

import com.example.readingisgood.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByCreatedByUser(String createdByUser, Pageable pageable);

    List<Order> findAllByCreatedByUserAndDateBetween(String createdByUser, Date start, Date end);

}
