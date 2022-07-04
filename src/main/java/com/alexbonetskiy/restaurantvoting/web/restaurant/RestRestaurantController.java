package com.alexbonetskiy.restaurantvoting.web.restaurant;


import com.alexbonetskiy.restaurantvoting.model.Restaurant;
import com.alexbonetskiy.restaurantvoting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class RestRestaurantController {

    public static final String REST_URL = "/api/restaurants";

    private RestaurantRepository repository;

    @GetMapping
    public List<Restaurant> getAllForToday() {
        log.info("getAllForToday");
        return repository.getAllForToday(LocalDate.now());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get with id={}", id);
        return ResponseEntity.of(repository.findById(id));
    }


    @GetMapping("/with-dishes")
    public List<Restaurant> getAllWithDishesForToday() {
        log.info("getAllWithDishesForToday");
        return repository.getAllWithDishesByDate(LocalDate.now());
    }

    @GetMapping("/{id}/with-dishes")
    public ResponseEntity<Restaurant> getWithDishesForToday(@PathVariable int id) {
        log.info("getWithDishesForToday with id={}", id);
        return ResponseEntity.of(repository.getWithDishesByDate(id, LocalDate.now()));
    }

}
