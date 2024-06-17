package com.hee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hee.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
//    Generate unique id automatically by using GenerationType.AUTO
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    private String password;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

//    Define user and order relationship
//    The mappedBy attribute tells that the @ManyToOne side is in charge of managing the Foreign Key column,
//    and the collection is used only to fetch the child entities and to cascade parent entity state changes
//    to children (e.g., removing the parent should also remove the child entities).
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
//    Just fetch data of users not List of orders so i use JsonIgnore to ignore it
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

//    favorites: User will store their favorite restaurants into this list
    @ElementCollection
    private List<RestaurantDto> favorites = new ArrayList();

//    Define user and address relationship
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

}
