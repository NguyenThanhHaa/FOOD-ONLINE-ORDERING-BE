package com.hee.controller;

import com.hee.model.Food;
import com.hee.model.Restaurant;
import com.hee.model.User;
import com.hee.request.CreateFoodRequest;
import com.hee.response.MessageResponse;
import com.hee.service.FoodService;
import com.hee.service.RestaurantService;
import com.hee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create-food")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt
                                           ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Food food = foodService.createFood(req,req.getFoodCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@RequestHeader("Authorization") String jwt,
                                                      @PathVariable Long id
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

         foodService.deleteFood(id);

         MessageResponse res = new MessageResponse();
         res.setMessage("Food was deleted successfully!!!");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/update-availability/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@RequestHeader("Authorization") String jwt,
                                                                  @PathVariable Long id
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.updateFoodAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }


}
