package com.alexbonetskiy.restaurantvoting.web.dish;

import com.alexbonetskiy.restaurantvoting.MatcherFactory;
import com.alexbonetskiy.restaurantvoting.model.Dish;

import java.time.LocalDate;


public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingEqualsComparator(Dish.class);
    

    public static final Dish BIG_MAC = new Dish(1, "Big Mac" ,1,  3, LocalDate.now());
    public static final Dish TWISTER = new Dish(3,"Twister", 2, 4, LocalDate.now());
    public static final Dish CHICKEN_WINGS = new Dish(4, "Chicken Wings", 2, 2, LocalDate.now().minusDays(1));
    public static final Dish WOPPER = new Dish(5, "Wopper", 3, 5, LocalDate.now());
    public static final Dish LONG_CHICKEN = new Dish(6, "Long Chicken",  4, 3, LocalDate.now().minusDays(1));

    public static Dish getNew() {return new Dish(null, "new", 1,1, LocalDate.now());}
    public static Dish getUpdated() {return new Dish(1, "updated", 1, 3, LocalDate.now()); }
    public static Dish getDishWithZeroPrice() {return new Dish(1, "zero price", 1, 0, LocalDate.now());}

}
