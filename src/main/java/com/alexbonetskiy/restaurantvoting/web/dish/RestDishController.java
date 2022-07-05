package com.alexbonetskiy.restaurantvoting.web.dish;


import com.alexbonetskiy.restaurantvoting.model.Dish;
import com.alexbonetskiy.restaurantvoting.repository.DishRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestDishController {

    static final String REST_URL = "/api/restaurants/dishes";

    private DishRepository repository;

    @GetMapping
    public List<Dish> getDishesForRestaurantForToday(@RequestParam int restaurantId) {
        log.info("getDishes for restaurant {} for today", restaurantId);
        return repository.getAllByRestaurantIdAndDate(restaurantId, LocalDate.now());
    }

}
