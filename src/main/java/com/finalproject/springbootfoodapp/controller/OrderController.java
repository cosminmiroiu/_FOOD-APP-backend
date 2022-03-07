package com.finalproject.springbootfoodapp.controller;

import com.finalproject.springbootfoodapp.entity.OrderProduct;
import com.finalproject.springbootfoodapp.entity.reqres.SetCourierToOrderRequest;
import com.finalproject.springbootfoodapp.entity.reqres.OrderRequest;
import com.finalproject.springbootfoodapp.entity.Order;
import com.finalproject.springbootfoodapp.enums.OrderStatus;
import com.finalproject.springbootfoodapp.service.OrderProductService;
import com.finalproject.springbootfoodapp.service.OrderService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductService orderProductService;


    /** GET MAPPING - ORDERS */

    // BASIC USER - get active orders
    @GetMapping("/active/user/{id}")
    @PreAuthorize("hasRole('STANDARD')")
    public ResponseEntity<List<Order>> getActiveOrdersByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getActiveOrdersByUserId(id), HttpStatus.OK);
    }

    // BASIC USER - get closed orders
    @GetMapping("/closed/user/{id}")
    @PreAuthorize("hasRole('STANDARD')")
    public ResponseEntity<List<Order>> getClosedOrdersByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getClosedOrdersByUserId(id), HttpStatus.OK);
    }

    // BASIC USER, RESTAURANT ADMIN, SUPER ADMIN - order details
    @GetMapping("/orderedProducts/{orderId}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'STANDARD', 'R_ADMINISTRATOR')")
    public ResponseEntity<List<OrderProduct>> getOrderedProductsByOrderId(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderProductService.getOrderedProductsByOrderId(orderId), HttpStatus.OK);
    }

    // COURIER - get accepted orders by courier providing courierId
    //         - get available orders that can be accepted by providing null courierId
    @GetMapping("/active/assignedTo/{courierId}")
    @PreAuthorize("hasRole('COURIER')")
    public ResponseEntity<List<Order>> getAcceptedOrdersAssignedToCourierId(@PathVariable Long courierId) {
        if (courierId == 0) {
            courierId = null;
        }

        return new ResponseEntity<>(orderService.getActiveOrdersAssignedToCourierId(courierId), HttpStatus.OK);
    }

    // RESTAURANT ADMIN - get all active orders ( not accepted by courier yet / accepted / delivering ) by restaurantId
    @GetMapping("/active/restaurant/{restaurantId}")
    @PreAuthorize("hasRole('R_ADMINISTRATOR')")
    public ResponseEntity<List<Order>> getActiveOrdersByRestaurant(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(orderService.getActiveOrdersByRestaurant(restaurantId), HttpStatus.OK);
    }


    /** GET MAPPING - COUNT ORDERS */

    @GetMapping("/count/today")
    @PreAuthorize("hasAnyRole('COURIER', 'R_ADMINISTRATOR', 'ADMINISTRATOR')")
    public ResponseEntity<Long> countTodayOrders() {
        return new ResponseEntity<>(orderService.countTodayOrders(), HttpStatus.OK);
    }

    @GetMapping("/count/courier/accepted/today/{courierId}")
    @PreAuthorize("hasRole('COURIER')")
    public ResponseEntity<Long> countTodayAcceptedOrdersByCourier(@PathVariable Long courierId) {
        return new ResponseEntity<>(orderService.countTodayAcceptedOrdersByCourier(courierId), HttpStatus.OK);
    }

    @GetMapping("/count/courier/delivered/today/{courierId}")
    @PreAuthorize("hasAnyRole('COURIER', 'R_ADMINISTRATOR', 'ADMINISTRATOR')")
    public ResponseEntity<Long> countTodayDeliveredOrdersByCourierId(@PathVariable Long courierId) {
        if (courierId == 0) {
            courierId = null;
        }

        return new ResponseEntity<>(orderService.countTodayDeliveredOrdersByCourierId(courierId), HttpStatus.OK);
    }

    @GetMapping("/count/courier/rejected/today/{courierId}")
    @PreAuthorize("hasAnyRole('COURIER', 'R_ADMINISTRATOR', 'ADMINISTRATOR')")
    public ResponseEntity<Long> countTodayRejectedOrdersByCourierId(@PathVariable Long courierId) {
        if (courierId == 0) {
            courierId = null;
        }

        return new ResponseEntity<>(orderService.countTodayRejectedOrdersByCourierId(courierId), HttpStatus.OK);
    }

    @GetMapping("/count/restaurant/today/{restaurantId}")
    @PreAuthorize("hasRole('R_ADMINISTRATOR')")
    public ResponseEntity<Long> countTodayPlacedOrdersByRestaurant(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(orderService.countTodayPlacedOrdersByRestaurant(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/count/restaurant/accepted/today/{restaurantId}")
    @PreAuthorize("hasRole('R_ADMINISTRATOR')")
    public ResponseEntity<Long> countTodayAcceptedOrdersByCouriersByRestaurant(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(orderService.countTodayAcceptedOrdersByCouriersByRestaurant(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/count/restaurant/delivering/{restaurantId}")
    @PreAuthorize("hasRole('R_ADMINISTRATOR')")
    public ResponseEntity<Long> countNowDeliveringOrdersByRestaurant(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(orderService.countNowDeliveringOrdersByRestaurant(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/count/restaurant/delivered/{restaurantId}")
    @PreAuthorize("hasRole('R_ADMINISTRATOR')")
    public ResponseEntity<Long> countTodayDeliveredOrdersByCouriersByRestaurant(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(orderService.countTodayByRestaurantByStatus(
                restaurantId,
                OrderStatus.DELIVERED), HttpStatus.OK
        );
    }

    @GetMapping("count/restaurant/rejected/{restaurantId}")
    @PreAuthorize("hasRole('R_ADMINISTRATOR')")
    public ResponseEntity<Long> countTodayRejectedOrdersByRestaurant(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(orderService.countTodayByRestaurantByStatus(
                restaurantId,
                OrderStatus.REJECTED), HttpStatus.OK
        );
    }


    /** PUT MAPPING - UPDATE ORDERS */

    @PutMapping("/courier/assignCourier")
    @PreAuthorize("hasRole('COURIER')")
    public ResponseEntity<String> acceptOrderByCourier(@RequestBody SetCourierToOrderRequest setCourierToOrderRequest) {
        return new ResponseEntity<>(orderService.acceptOrderByCourier(setCourierToOrderRequest), HttpStatus.OK);
    }

    @PutMapping("/courier/pickOrder")
    @PreAuthorize("hasRole('COURIER')")
    public ResponseEntity<String> setOrderPickedByCourier(@RequestBody Long orderId) {
        return new ResponseEntity<>(orderService.setOrderPickedByCourier(orderId), HttpStatus.OK);
    }

    @PutMapping("/courier/successfullyDelivered")
    @PreAuthorize("hasRole('COURIER')")
    public ResponseEntity<String> setOrderAsDelivered(@RequestBody Long orderId) {
        return new ResponseEntity<>(orderService.setOrderAsDelivered(orderId), HttpStatus.OK);
    }

    @PutMapping("/courier/autoAcceptOrder")
    @PreAuthorize("hasRole('COURIER')")
    public ResponseEntity<String> autoAcceptOrder(@RequestBody Long courierId) {
        return new ResponseEntity<>(orderService.autoAcceptOrder(courierId), HttpStatus.OK);
    }


    /** POST MAPPING - SAVE ORDER */

    @PostMapping("/add")
    @PreAuthorize("hasRole('STANDARD')")
    public String placeOrder(@NotNull @RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

}