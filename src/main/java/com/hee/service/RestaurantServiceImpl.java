package com.hee.service;

import com.hee.dto.RestaurantDto;
import com.hee.model.Address;
import com.hee.model.Restaurant;
import com.hee.model.User;
import com.hee.repository.AddressRepository;
import com.hee.repository.RestaurantRepository;
import com.hee.repository.UserRepository;
import com.hee.request.CreateRestaurantRequest;
import com.hee.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }

        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());

        }

        if(restaurant.getName()!=null){
            restaurant.setName(updatedRestaurant.getName());
        }

        if(restaurant.getImages()!= null){
            restaurant.setImages(updatedRestaurant.getImages());
        }

        if(restaurant.getContactInformation()!=null){
            restaurant.setContactInformation(updatedRestaurant.getContactInformation());
        }

        if(restaurant.getOpeningHours()!=null){
            restaurant.setOpeningHours(updatedRestaurant.getOpeningHours());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {

        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {

        Optional<Restaurant> opt = restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Restaurant not found with id: " +id);
        }

        return opt.get();
    }

    @Override
    public List<Restaurant> getRestaurantByUserId(Long userId) throws Exception {
        List<Restaurant> restaurants = restaurantRepository.findByOwnerId(userId);

        if (restaurants.isEmpty()) {
            throw new Exception("Restaurant not found with user id: " + userId);
        }

        return restaurants;
    }


    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();

        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        MessageResponse response = new MessageResponse();

        if(user.getFavorites().contains(dto)){
            user.getFavorites().remove(dto);
        }

        else user.getFavorites().add(dto);

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {

        Restaurant restaurant = findRestaurantById(id);

        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantByIdAndUserId(Long restaurantId, Long userId) throws Exception {
        return restaurantRepository.findByIdAndUserId(restaurantId, userId);
    }
}
