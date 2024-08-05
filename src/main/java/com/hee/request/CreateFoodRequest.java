package com.hee.request;

import com.hee.model.Category;
import com.hee.model.IngredientsItem;
import com.hee.model.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;

    private String description;

    private Long price;

    private Category foodCategory;

    private List<String> images;

    private Long restaurantId;

    private boolean isVegetarian;

    private boolean isSeasonal;

    private List<IngredientsItem> ingredients;
}
