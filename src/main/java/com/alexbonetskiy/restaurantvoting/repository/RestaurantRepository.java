package com.alexbonetskiy.restaurantvoting.repository;


import com.alexbonetskiy.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer >, BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE d.date=:date ORDER BY r.name, d.name")
    List<Restaurant> getAllWithDishesByDate(LocalDate date);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE r.id=:id and d.date=:date ORDER BY r.name, d.name")
    Optional<Restaurant> getWithDishesByDate(int id, LocalDate date);

    @Query("SELECT r FROM Restaurant r JOIN r.dishes d WHERE d.date=:date ORDER BY r.name, d.name")
    List<Restaurant> getAllForToday(LocalDate date);

}

