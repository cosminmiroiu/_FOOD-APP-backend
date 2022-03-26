package com.finalproject.springbootfoodapp.controller;

import com.finalproject.springbootfoodapp.entity.ProductCategory;
import com.finalproject.springbootfoodapp.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        return new ResponseEntity<>(productCategoryService.findAllCategories(), HttpStatus.OK);
    }
}
