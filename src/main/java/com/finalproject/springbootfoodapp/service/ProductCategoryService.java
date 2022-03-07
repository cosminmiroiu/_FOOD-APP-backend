package com.finalproject.springbootfoodapp.service;

import com.finalproject.springbootfoodapp.entity.ProductCategory;
import com.finalproject.springbootfoodapp.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public void addProductCategory(ProductCategory productCategory) {
        if (productCategoryRepository.findByCategoryName(productCategory.getCategoryName()).isEmpty()) {
            productCategoryRepository.save(productCategory);
        }
    }

    public List<ProductCategory> findAllCategories() {
        return this.productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> getCategoryByName(String name) {
        return productCategoryRepository.findByCategoryName(name);
    }

}
