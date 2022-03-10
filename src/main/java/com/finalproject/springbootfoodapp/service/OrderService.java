package com.finalproject.springbootfoodapp.service;

import com.finalproject.springbootfoodapp.entity.*;
import com.finalproject.springbootfoodapp.entity.reqres.SetCourierToOrderRequest;
import com.finalproject.springbootfoodapp.entity.reqres.OrderRequest;
import com.finalproject.springbootfoodapp.enums.OrderStatus;
import com.finalproject.springbootfoodapp.exception.RepoException;
import com.finalproject.springbootfoodapp.repository.OrderRepository;
import com.finalproject.springbootfoodapp.repository.UserRepository;
import com.finalproject.springbootfoodapp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DateUtil dateUtil;


    /** for basic-user: save order to database */
    public String placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();

        String orderUUID = generateOrderUUID();
        order.setOrderUUID(orderUUID);

        order.setAssignedTo(null);

        order.setActive(true);

        order.setStatus(OrderStatus.PENDING.getInfo());

        Set<OrderProduct> orderProducts = orderRequest.getOrderProducts();
        orderProducts.forEach(order::addOrderedProduct);

        User user = orderRequest.getUser();
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());

        if (userFromDb.isPresent()) {
            user = userFromDb.get();
        } else {
            throw new RepoException("User information is invalid.");
        }

        user.addOrder(order);
        userRepository.save(user);

        Optional<Order> placedOrder = orderRepository.findByOrderUUID(orderUUID);

        if (placedOrder.isEmpty()) {
            throw new RepoException("Failed to save order.");
        }

        return placedOrder.get().getId().toString();
    }

    /** for basic-user: get all active orders by user id */
    public List<Order> getActiveOrdersByUserId(Long userId) {
        Optional<List<Order>> orders;
        orders = orderRepository.findAllByUserIdAndActiveTrueOrderByDateCreatedDesc(userId);

        if (orders.isPresent()) {
            return orders.get();
        } else {
            throw new RepoException("No orders have been added yet for this user.");
        }
    }

    /** for basic-user: get all closed orders by user id */
    public List<Order> getClosedOrdersByUserId(Long userId) {
        Optional<List<Order>> orders;
        orders = orderRepository.findAllByUserIdAndActiveFalseOrderByDateCreatedDesc(userId);

        if (orders.isPresent()) {
            return orders.get();
        } else {
            throw new RepoException("No orders have been added yet for this user.");
        }
    }

    /** for courier-user: get active orders by assigned courier
                          also can be used to get all available orders providing null id */
    public List<Order> getActiveOrdersAssignedToCourierId(Long courierId) {

        Optional<List<Order>> orders;

        orders = orderRepository.findAllByActiveTrueAndAssignedToIdOrderByDateCreated(courierId);

        if (orders.isPresent()) {
            return orders.get();
        } else {
            throw new RepoException("No active orders for the moment.");
        }
    }

    /** for courier-user: assign courier to order */
    public String acceptOrderByCourier(SetCourierToOrderRequest setCourierToOrderRequest) {

        Optional<Order> orderToCheck;
        orderToCheck = orderRepository.findById(setCourierToOrderRequest.getOrderId());

        Optional<User> courierToCheck;
        courierToCheck = userRepository.findById(setCourierToOrderRequest.getCourierId());

        if (orderToCheck.isEmpty() || courierToCheck.isEmpty()) {
            return "There was an error while assigning courier to order. Please retry.";
        }

        if (orderToCheck.get().getAssignedTo() != null) {
            return "Another courier is assigned to this order.";
        }

        User courierUser = courierToCheck.get();
        Order order = orderToCheck.get();

        order.setAssignedTo(courierUser);
        order.setStatus(OrderStatus.ACCEPTED.getInfo());

        orderRepository.save(order);

        return "Order successfully accepted!";

    }

    /** for courier-user: pick up the order from restaurant */
    public String setOrderPickedByCourier(Long orderId) {

        Optional<Order> orderToCheck = orderRepository.findById(orderId);

        if (orderToCheck.isEmpty()) {
            return "Order not found. Please refresh the list and try again.";
        }

        Order order = orderToCheck.get();

        if (!order.getStatus().equals(OrderStatus.ACCEPTED.getInfo())) {
            return "Cannot pick up this order. Check the order status.";
        }

        order.setStatus(OrderStatus.PICKED_UP.getInfo());

        orderRepository.save(order);

        return "Order successfully picked up!";
    }

    /** for courier-user: order successfully delivered to client */
    public String setOrderAsDelivered(Long orderId) {

        Optional<Order> orderToCheck = orderRepository.findById(orderId);

        if (orderToCheck.isEmpty()) {
            return "Order not found. Please refresh the list and try again.";
        }

        Order order = orderToCheck.get();

        if (!order.getStatus().equals(OrderStatus.PICKED_UP.getInfo())) {
            return "Cannot set this order as delivered. Check the order status.";
        }

        order.setStatus(OrderStatus.DELIVERED.getInfo());
        order.setActive(false);

        orderRepository.save(order);

        return "Order successfully set as delivered!";

    }

    /** for courier-user / restaurant admin user / super admin user: count all orders placed today */
    public Long countTodayOrders() {

        Date startOfDay = dateUtil.getDayStart(new Date());
        Date endOfDay = dateUtil.getDayEnd(new Date());

        return orderRepository.countByDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
                startOfDay,
                endOfDay
        );
    }

    /** for courier-user: count all orders accepted by courier today */
    public Long countTodayAcceptedOrdersByCourier(Long courierId) {

        Date startOfDay = dateUtil.getDayStart(new Date());
        Date endOfDay = dateUtil.getDayEnd(new Date());

        return orderRepository.countByAssignedToIdAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
                courierId,
                startOfDay,
                endOfDay
        );
    }

    /** for courier-user / restaurant admin user / super admin user:
        - count orders delivered today by courier */
    public Long countTodayDeliveredOrdersByCourierId(Long courierId) {

        Date startOfDay = dateUtil.getDayStart(new Date());
        Date endOfDay = dateUtil.getDayEnd(new Date());

        return orderRepository.countByAssignedToIdAndStatusIsAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
                courierId,
                OrderStatus.DELIVERED.getInfo(),
                startOfDay,
                endOfDay
        );
    }

    /** for courier-user / restaurant admin user / super admin user:
        - count today rejected orders - for courier by providing courier id
        - count all today rejected orders by providing null courier id */
    public Long countTodayRejectedOrdersByCourierId(Long courierId) {

        Date startOfDay = dateUtil.getDayStart(new Date());
        Date endOfDay = dateUtil.getDayEnd(new Date());

        return orderRepository.countByAssignedToIdAndStatusIsAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
                courierId,
                OrderStatus.REJECTED.getInfo(),
                startOfDay,
                endOfDay
        );
    }

    /** for courier-user: auto accept order if checkbox is checked on client UI */
    public String autoAcceptOrder(Long courierId) {

        Optional<Order> orderToCheck = orderRepository.findTop1ByActiveTrueAndAssignedToNullOrderByDateCreated();

        if (orderToCheck.isEmpty()) {
            return "No available orders at the moment. Stay focus.";
        }

        Order order = orderToCheck.get();

        Optional<User> userToCheck = userRepository.findById(courierId);

        if (userToCheck.isEmpty()) {
            return "Courier user not found.";
        }

        User courierUser = userToCheck.get();

        order.setAssignedTo(courierUser);

        order.setStatus(OrderStatus.ACCEPTED.getInfo());

        orderRepository.save(order);

        return "Order successfully accepted.";

    }

    /** for restaurant admin-user: get all active orders */
    public List<Order> getActiveOrdersByRestaurant(Long restaurantId) {

        Optional<List<Order>> ordersToCheck = orderRepository.findAllByRestaurantIdAndActiveTrueAndStatusIsNotOrderByDateCreatedDesc(
                restaurantId,
                OrderStatus.PICKED_UP.getInfo());

        if (ordersToCheck.isEmpty()) {
            throw new RepoException("No orders for the moment.");
        }

        List<Order> orders = ordersToCheck.get();

        Collections.sort(orders);

        return orders;

    }

    /** for restaurant admin-user: count today placed orders by clients for restaurant */
    public Long countTodayPlacedOrdersByRestaurant(Long restaurantId) {

        Date startOfDay = dateUtil.getDayStart(new Date());
        Date endOfDay = dateUtil.getDayEnd(new Date());

        return orderRepository.countByRestaurantIdAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
                restaurantId,
                startOfDay,
                endOfDay
        );
    }

    /** for restaurant admin-user: count today accepted orders by couriers for restaurant */
    public Long countTodayAcceptedOrdersByCouriersByRestaurant(Long restaurantId) {

        Date startOfDay = dateUtil.getDayStart(new Date());
        Date endOfDay = dateUtil.getDayEnd(new Date());

        return orderRepository.countByRestaurantIdAndAssignedToIdNotNullAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
                restaurantId,
                startOfDay,
                endOfDay
        );
    }

    /** for restaurant admin-user: count active orders which are picked up by couriers right now by restaurant */
    public Long countNowDeliveringOrdersByRestaurant(Long restaurantId) {

        return orderRepository.countByActiveTrueAndRestaurantIdAndStatusIs(
                restaurantId,
                OrderStatus.PICKED_UP.getInfo());
    }

    /** for restaurant admin-user: count today orders by restaurant by order status */
    public Long countTodayByRestaurantByStatus(Long restaurantId, OrderStatus status) {

        Date startOfDay = dateUtil.getDayStart(new Date());
        Date endOfDay = dateUtil.getDayEnd(new Date());

        return orderRepository.countByRestaurantIdAndActiveFalseAndStatusIsAndDateCreatedGreaterThanAndDateCreatedLessThanEqual(
                restaurantId,
                status.getInfo(),
                startOfDay,
                endOfDay
        );
    }

    private String generateOrderUUID() {
        return UUID.randomUUID().toString();
    }
}
