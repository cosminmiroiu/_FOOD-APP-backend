package com.finalproject.springbootfoodapp.service;

import com.finalproject.springbootfoodapp.entity.OrderProduct;
import com.finalproject.springbootfoodapp.exception.RepoException;
import com.finalproject.springbootfoodapp.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    public List<OrderProduct> getOrderedProductsByOrderId(Long orderId) {
        Optional<List<OrderProduct>> orderProducts = orderProductRepository.findAllByOrderId(orderId);

        if (orderProducts.isEmpty()) {
            throw new RepoException("Products not found for order id: " + orderId + ".");
        } else {
            return orderProducts.get();
        }
    }
}
