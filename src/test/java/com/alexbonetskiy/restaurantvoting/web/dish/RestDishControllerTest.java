package com.alexbonetskiy.restaurantvoting.web.dish;

import com.alexbonetskiy.restaurantvoting.web.AbstractRestControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.alexbonetskiy.restaurantvoting.web.dish.DishTestData.BIG_MAC;
import static com.alexbonetskiy.restaurantvoting.web.dish.RestDishController.REST_URL;
import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestDishControllerTest extends AbstractRestControllerTest {



    @Test
    @WithUserDetails(value = USER_MAIL)
    void getMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("restaurantId","1"))
                .andExpect(status().isOk())
                .andExpect(DishTestData.DISH_MATCHER.contentJson(List.of(BIG_MAC)));
    }
}
