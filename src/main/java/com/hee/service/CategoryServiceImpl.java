package com.hee.service;

import com.hee.model.Category;
import com.hee.model.Restaurant;
import com.hee.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId, Long restaurantId) throws Exception {
        // Tìm nhà hàng theo restaurantId và userId để đảm bảo đúng user
        Restaurant restaurant = restaurantService.getRestaurantByIdAndUserId(restaurantId, userId);

        if (restaurant == null) {
            throw new Exception("No restaurant found for the given restaurantId and userId.");
        }

        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }


    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant =  restaurantService.findRestaurantById(id);

        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("Category is not found!");
        }

        return optionalCategory.get();
    }
}
