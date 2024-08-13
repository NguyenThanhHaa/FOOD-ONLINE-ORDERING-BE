package com.hee.repository;

import com.hee.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) " +
            "OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")
    List<Restaurant> findBySearchQuery(String query);

    List<Restaurant> findByOwnerId(Long userId);

    @Query("SELECT r FROM Restaurant r WHERE r.id = :id AND r.owner.id = :userId")
    Restaurant findByIdAndUserId(Long id, Long userId);
}
