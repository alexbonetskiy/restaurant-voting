package com.alexbonetskiy.restaurantvoting.repository;


import com.alexbonetskiy.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer > {
}
