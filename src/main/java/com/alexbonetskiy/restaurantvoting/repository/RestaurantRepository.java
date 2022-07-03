package com.alexbonetskiy.restaurantvoting.repository;


import com.alexbonetskiy.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer > {

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE d.date=:date ORDER BY r.name, d.name")
    List<Restaurant> getAllWithDishes(LocalDate date);

}

