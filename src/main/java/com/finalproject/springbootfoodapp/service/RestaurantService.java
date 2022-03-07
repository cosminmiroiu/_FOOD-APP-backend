package com.finalproject.springbootfoodapp.service;

import com.finalproject.springbootfoodapp.entity.Restaurant;
import com.finalproject.springbootfoodapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpTimeoutException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> findAllRestaurants() {

        return restaurantRepository.findAll();
    }

    public void addRestaurant(Restaurant restaurant) {
        if(restaurantRepository.findByRestaurantName(restaurant.getRestaurantName()).isEmpty()) {
            restaurantRepository.save(restaurant);
        }
    }

    public Optional<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findByRestaurantName(name);
    }

    public Restaurant getRestaurantById(Long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        return restaurant.get();
    }
}
