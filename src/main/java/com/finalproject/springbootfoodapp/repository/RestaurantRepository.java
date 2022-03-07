package com.finalproject.springbootfoodapp.repository;

import com.finalproject.springbootfoodapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findById(Long id);

    Optional<Restaurant> findByRestaurantName(String name);
}
