package com.alexbonetskiy.restaurantvoting.web.vote;

import com.alexbonetskiy.restaurantvoting.model.Vote;
import com.alexbonetskiy.restaurantvoting.repository.VoteRepository;
import com.alexbonetskiy.restaurantvoting.service.VoteService;
import com.alexbonetskiy.restaurantvoting.web.AbstractRestControllerTest;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.alexbonetskiy.restaurantvoting.web.restaurant.RestaurantTestData.BURGER_KING;
import static com.alexbonetskiy.restaurantvoting.web.restaurant.RestaurantTestData.MAC_DONALDS;
import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.*;
import static com.alexbonetskiy.restaurantvoting.web.vote.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestVoteControllerTest extends AbstractRestControllerTest {


    private static final String REST_URL = RestVoteController.REST_URL + "/";

    private final LocalTime deadline = LocalTime.of(11, 0);

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteService service;


    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void createWithLocation() throws Exception {
        Vote newVote = VoteTestData.getNew();

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", String.valueOf(MAC_DONALDS.id())));

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.findById(newId).orElse(null), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getVoteForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "today")
                .param("date", LocalDate.now().toString()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE1));

    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateAfterDeadline() throws Exception {
        VoteService.setDeadline(LocalTime.MIN);
        Vote updatedVote = VoteTestData.getUpdated();

        perform(MockMvcRequestBuilders.put(REST_URL)
                .param("voteId", String.valueOf(updatedVote.id()))
                .param("restaurantId", String.valueOf(BURGER_KING.id())))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateBeforeDeadline() throws Exception {
        VoteService.setDeadline(LocalTime.MAX);
        Vote updatedVote = VoteTestData.getUpdated();

        perform(MockMvcRequestBuilders.put(REST_URL)
                .param("voteId", String.valueOf(updatedVote.id()))
                .param("restaurantId", String.valueOf(BURGER_KING.id())));

        VOTE_MATCHER.assertMatch(service.get( USER.id(), updatedVote.getDate()).orElse(null), updatedVote);

    }


}
