package com.alexbonetskiy.restaurantvoting.web.dish;

import com.alexbonetskiy.restaurantvoting.model.Dish;

import java.time.LocalDate;



public class DishTestData {

    public static final Dish BIG_MAC = new Dish(1, "Big Mac" ,3, LocalDate.now());
    public static final Dish TWISTER = new Dish(3,"Twister", 4, LocalDate.now());
    public static final Dish CHICKEN_WINGS = new Dish(4, "Chicken Wings", 2, LocalDate.now().minusDays(1));
    public static final Dish WOPPER = new Dish(5, "Wopper", 5, LocalDate.now());
    public static final Dish LONG_CHICKEN = new Dish(6, "Long Chicken",  3, LocalDate.now().minusDays(1));

}
