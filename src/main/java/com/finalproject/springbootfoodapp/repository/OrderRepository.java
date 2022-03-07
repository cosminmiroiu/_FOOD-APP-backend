package com.finalproject.springbootfoodapp.repository;

import com.finalproject.springbootfoodapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /** find order by order id */
    Optional<Order> findById(Long id);

    /** for courier user - get first available order */
    Optional<Order> findTop1ByActiveTrueAndAssignedToNullOrderByDateCreated();

    /** basic user - get order by UUID */
    Optional<Order> findByOrderUUID(String orderUUID);

    /** basic user - get all active orders */
    Optional<List<Order>> findAllByUserIdAndActiveTrueOrderByDateCreatedDesc(Long userId);

    /** basic user - get all closed orders */
    Optional<List<Order>> findAllByUserIdAndActiveFalseOrderByDateCreatedDesc(Long userId);

    /** courier user - get all orders assigned to courier by providing courier id
                       MULTI USE: get all available orders by providing null courier id */
    Optional<List<Order>> findAllByActiveTrueAndAssignedToIdOrderByDateCreated(Long id);

    /** courier user / restaurant admin user - count all orders by day */
    Long countByDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(Date dayStart, Date dayEnd);

    /** courier user - count all accepted orders by courier by day */
    Long countByAssignedToIdAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
            Long courierId,
            Date dayStart,
            Date dayEnd
    );

    /** courier user - count all accepted orders with status (delivered/rejected) by courier by day */
    Long countByAssignedToIdAndStatusIsAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
            Long courierId,
            String status,
            Date dayStart,
            Date dayEnd
    );

    /** restaurant admin user - get all active orders */
    Optional<List<Order>> findAllByRestaurantIdAndActiveTrueAndStatusIsNotOrderByDateCreatedDesc(
            Long restaurantId,
            String status);


    /** restaurant admin user - count all placed orders for restaurant by day */
    Long countByRestaurantIdAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
            Long restaurantId,
            Date dayStart,
            Date dayEnd
    );

    /** restaurant admin user - count all accepted orders by couriers for restaurant by day */
    Long countByRestaurantIdAndAssignedToIdNotNullAndDateCreatedGreaterThanEqualAndDateCreatedLessThanEqual(
            Long restaurantId,
            Date dayStart,
            Date dayEnd
    );

    /** restaurant admin user - count delivering orders right now by couriers */
    Long countByActiveTrueAndRestaurantIdAndStatusIs(Long restaurantId, String status);

    /** restaurant admin user - count delivered / rejected orders by day by restaurant */
    Long countByRestaurantIdAndActiveFalseAndStatusIsAndDateCreatedGreaterThanAndDateCreatedLessThanEqual(
            Long restaurantId,
            String status,
            Date dayStart,
            Date dayEnd
    );

}
