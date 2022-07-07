package com.alexbonetskiy.restaurantvoting.web.vote;

import com.alexbonetskiy.restaurantvoting.MatcherFactory;
import com.alexbonetskiy.restaurantvoting.model.Vote;

import java.time.LocalDate;

import static com.alexbonetskiy.restaurantvoting.web.restaurant.RestaurantTestData.BURGER_KING;
import static com.alexbonetskiy.restaurantvoting.web.restaurant.RestaurantTestData.MAC_DONALDS;
import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.ADMIN;
import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.USER;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");
    public static final MatcherFactory.Matcher<Vote> VOTE_WITH_USER_AND_RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant.dishes", "user.password");

    public static final Vote VOTE1 = new Vote(1, LocalDate.now());
    public static final Vote VOTE2 = new Vote(2, LocalDate.now());
    public static final Vote VOTE3 = new Vote(3, LocalDate.now().minusDays(1));
    public static final Vote VOTE4 = new Vote(4,LocalDate.now().minusDays(2));


    static {
        VOTE1.setUser(USER);
        VOTE2.setUser(ADMIN);
        VOTE3.setUser(USER);
        VOTE4.setUser(USER);

        VOTE1.setRestaurant(MAC_DONALDS);
        VOTE2.setRestaurant(MAC_DONALDS);
        VOTE3.setRestaurant(BURGER_KING);
        VOTE4.setRestaurant(MAC_DONALDS);
    }

    public static Vote getNew() {
        return new Vote(null, LocalDate.now());
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1.id(), LocalDate.now());
    }
}
