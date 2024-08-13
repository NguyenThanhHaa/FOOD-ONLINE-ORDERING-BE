package com.hee.service;

import com.hee.dto.RestaurantDto;
import com.hee.model.Restaurant;
import com.hee.model.User;
import com.hee.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public List<Restaurant> getRestaurantByUserId(Long userId) throws  Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;

    public Restaurant getRestaurantByIdAndUserId(Long restaurantId, Long userId) throws Exception;



}
