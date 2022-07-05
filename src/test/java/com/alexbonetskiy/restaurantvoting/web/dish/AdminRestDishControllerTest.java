package com.alexbonetskiy.restaurantvoting.web.dish;

import com.alexbonetskiy.restaurantvoting.model.Dish;
import com.alexbonetskiy.restaurantvoting.repository.DishRepository;
import com.alexbonetskiy.restaurantvoting.util.JsonUtil;
import com.alexbonetskiy.restaurantvoting.web.AbstractRestControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.alexbonetskiy.restaurantvoting.web.dish.DishTestData.*;
import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminRestDishControllerTest extends AbstractRestControllerTest {

    static final String REST_URL = AdminRestDishController.REST_URL + "/";

    @Autowired
    DishRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + BIG_MAC.id()))
                .andExpect(status().isOk())
                .andExpect(DISH_MATCHER.contentJson(BIG_MAC));
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteById() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + TWISTER.id()))
                .andExpect(status().isNoContent());
        Assertions.assertNull(repository.findById(TWISTER.id()).orElse(null));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL + getUpdated().id() )
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(getUpdated())))
                .andExpect(status().isNoContent());

       DISH_MATCHER.assertMatch(repository.findById(BIG_MAC.id()).orElse(null), DishTestData.getUpdated());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateByZeroPrice() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL + BIG_MAC.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(DishTestData.getDishWithZeroPrice())))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(DishTestData.getNew())))
                .andExpect(status().isCreated());

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(repository.findById(newId).orElse(null), newDish);
    }
    }