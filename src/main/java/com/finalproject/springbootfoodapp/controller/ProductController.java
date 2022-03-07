package com.finalproject.springbootfoodapp.controller;

import com.finalproject.springbootfoodapp.entity.Product;
import com.finalproject.springbootfoodapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Product>> getRestaurantProducts(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductsByRestaurantId(id), HttpStatus.OK);
    }

    @GetMapping("/count/restaurant/{id}")
    public ResponseEntity<Long> getProductsCountByRestaurant(@PathVariable Long id) {
        return new ResponseEntity<>(productService.countProductsByRestaurantId(id), HttpStatus.OK);
    }

    @GetMapping("/5-discounted-products")
    public ResponseEntity<List<Product>> get5RandomDiscountedProducts() {
        return new ResponseEntity<>(productService.get5RandomDiscountedProducts(), HttpStatus.OK);
    }
}
