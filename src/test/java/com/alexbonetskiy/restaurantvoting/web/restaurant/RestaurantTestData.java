package com.alexbonetskiy.restaurantvoting.web.restaurant;

import com.alexbonetskiy.restaurantvoting.MatcherFactory;
import com.alexbonetskiy.restaurantvoting.model.Restaurant;

import java.util.List;

import static com.alexbonetskiy.restaurantvoting.web.dish.DishTestData.*;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_DISHES_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final Restaurant MAC_DONALDS = new Restaurant(1, "MacDonalds", List.of(BIG_MAC));
    public static final Restaurant KFC = new Restaurant(2, "KFC", List.of(TWISTER, CHICKEN_WINGS));
    public static final Restaurant BURGER_KING = new Restaurant(3, "Burger King", List.of(WOPPER, LONG_CHICKEN));

    public static Restaurant getNew() {
        return new Restaurant(null, "new Restaurant", null);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(1, "updated ", MAC_DONALDS.getDishes());
    }

    public static Restaurant getNull() {return new Restaurant(null, null, null);}
}
