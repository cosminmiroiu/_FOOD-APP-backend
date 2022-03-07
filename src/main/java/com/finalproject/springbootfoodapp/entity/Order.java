package com.finalproject.springbootfoodapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order implements Comparable<Order> {

    @Id
    @GenericGenerator(
            name = "START_FROM_1000",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "order_sequence_rule"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "START_FROM_1000")
    private Long id;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "order_uuid")
    private String orderUUID;

    @Column(name = "total_qty")
    private Integer totalQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "delivery_user_id")
    @Nullable
    private User assignedTo;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "order_status")
    private String status;

    @Column(name = "reason")
    @Nullable
    private String reason;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_status_update")
    @UpdateTimestamp
    private Date dateUpdated;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonIgnore
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public void addOrderedProduct(OrderProduct orderProduct) {
        if (orderProduct != null) {
            if (orderProducts == null) {
                orderProducts = new HashSet<>();
            }
            orderProducts.add(orderProduct);
            orderProduct.setOrder(this);
        }
    }

    @Override
    public int compareTo(Order order) {
        if (getStatus() == null || order.getStatus() == null) {
            return 0;
        }
        return getStatus().compareTo(order.getStatus());
    }
}