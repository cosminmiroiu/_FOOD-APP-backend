package com.finalproject.springbootfoodapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "restaurant_name", unique = true)
    private String restaurantName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "restaurant_address")
    private String restaurantAddress;

    @Column(name = "delivery_price")
    private Long deliveryPrice;

}
