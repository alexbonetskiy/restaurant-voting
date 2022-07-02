package com.alexbonetskiy.restaurantvoting.repository;

import com.alexbonetskiy.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
