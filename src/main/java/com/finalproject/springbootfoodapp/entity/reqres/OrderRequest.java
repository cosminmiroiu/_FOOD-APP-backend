package com.finalproject.springbootfoodapp.entity.reqres;

import com.finalproject.springbootfoodapp.entity.Order;
import com.finalproject.springbootfoodapp.entity.OrderProduct;
import com.finalproject.springbootfoodapp.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderRequest {

    private User user;
    private Order order;
    private Set<OrderProduct> orderProducts;
}
