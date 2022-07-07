package com.alexbonetskiy.restaurantvoting.web.vote;

import com.alexbonetskiy.restaurantvoting.web.AbstractRestControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static com.alexbonetskiy.restaurantvoting.web.restaurant.RestaurantTestData.*;
import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static com.alexbonetskiy.restaurantvoting.web.vote.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AdminRestVoteControllerTest extends AbstractRestControllerTest {

    private static final String REST_URL = AdminRestVoteController.REST_URL + "/";

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllByRestaurantIdAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MAC_DONALDS.id() + "/by-date")
                .param("restaurantId", String.valueOf(MAC_DONALDS.id()))
                .param("date", LocalDate.now().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_WITH_USER_AND_RESTAURANT_MATCHER.contentJson(List.of(VOTE1)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWinnerSolo() throws Exception {
       perform(MockMvcRequestBuilders.get(REST_URL + "winner")
                .param("date", LocalDate.now().minusDays(1).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(List.of(BURGER_KING)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWinnerEmptyVotes() throws Exception {
         perform(MockMvcRequestBuilders.get(REST_URL + "winner")
                .param("date", LocalDate.now().minusDays(3).toString()))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWinnerList() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "winner")
                .param("date", LocalDate.now().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(List.of(MAC_DONALDS, KFC)));


    }
}