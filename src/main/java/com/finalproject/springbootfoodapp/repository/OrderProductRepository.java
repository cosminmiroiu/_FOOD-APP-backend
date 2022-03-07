package com.finalproject.springbootfoodapp.repository;

import com.finalproject.springbootfoodapp.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    Optional<List<OrderProduct>> findAllByOrderId(Long orderId);
}
