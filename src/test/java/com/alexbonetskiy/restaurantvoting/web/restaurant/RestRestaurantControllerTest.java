package com.alexbonetskiy.restaurantvoting.web.restaurant;

import com.alexbonetskiy.restaurantvoting.web.AbstractRestControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.alexbonetskiy.restaurantvoting.web.restaurant.RestaurantTestData.*;
import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestRestaurantControllerTest extends AbstractRestControllerTest {

    private static final String REST_URL = RestRestaurantController.REST_URL + "/";

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.RESTAURANT_MATCHER.contentJson(BURGER_KING, KFC, MAC_DONALDS));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MAC_DONALDS.id()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.RESTAURANT_MATCHER.contentJson(MAC_DONALDS));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithDishesForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "with-dishes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_DISHES_MATCHER.contentJson(BURGER_KING, KFC, MAC_DONALDS));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getWithDishesForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MAC_DONALDS.id() + "/with-dishes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_DISHES_MATCHER.contentJson(MAC_DONALDS));
    }
}