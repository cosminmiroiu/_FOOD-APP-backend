package com.finalproject.springbootfoodapp.service;

import com.finalproject.springbootfoodapp.entity.Product;
import com.finalproject.springbootfoodapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        if (productRepository.findByName(product.getName()).isEmpty()) {
            this.productRepository.save(product);
        }
    }

    public List<Product> getProductsByRestaurantId(Long id) {
        return productRepository.findAllByRestaurantId(id);
    }

    public List<Product> get5RandomDiscountedProducts() {

        List<Product> discountedProducts = productRepository.findAllByDiscountPercentGreaterThan(0L);

        if (discountedProducts.size() > 0) {
            Collections.shuffle(discountedProducts);
        } else {
            return null;
        }

        if (discountedProducts.size() <= 5) { return discountedProducts; }

        return discountedProducts.stream().limit(5).collect(Collectors.toList());
    }

    public Long countProductsByRestaurantId(Long id) { return productRepository.countByRestaurantId(id); }

}
