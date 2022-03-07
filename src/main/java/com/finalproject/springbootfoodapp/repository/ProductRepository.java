package com.finalproject.springbootfoodapp.repository;

import com.finalproject.springbootfoodapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findAllByRestaurantId(Long id);

    List<Product> findAllByDiscountPercentGreaterThan(Long percentValue);

    Long countByRestaurantId(Long id);
}
