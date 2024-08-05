package com.hee.service;

import com.hee.model.Category;
import com.hee.model.Food;
import com.hee.model.Restaurant;
import com.hee.request.CreateFoodRequest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req,
                           Category category,
                           Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food>  getRestaurantsFood(Long restaurantId,
                                          boolean isVegetarian,
                                          boolean isNonvegetarian,
                                          boolean isSeasonal,
                                          String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateFoodAvailabilityStatus(Long foodId) throws Exception;

//    public Food updateFoodInfo(Long foodId) throws Exception;

}
