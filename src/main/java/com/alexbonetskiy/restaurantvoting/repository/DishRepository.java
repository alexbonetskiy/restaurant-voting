package com.alexbonetskiy.restaurantvoting.repository;

import com.alexbonetskiy.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer>, BaseRepository<Dish> {

   @Query("SELECT d FROM Dish d  WHERE d.restaurantId=:id  and d.date=:date ORDER BY d.name")
   List<Dish> getAllByRestaurantIdAndDate (int id, LocalDate date);

}
