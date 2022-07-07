package com.alexbonetskiy.restaurantvoting.web.vote;


import com.alexbonetskiy.restaurantvoting.model.Restaurant;
import com.alexbonetskiy.restaurantvoting.model.Vote;
import com.alexbonetskiy.restaurantvoting.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminRestVoteController {

    public static final String REST_URL = "/api/admin/votes";

    private VoteService service;

    @GetMapping("/by-date")
    public List<Vote> getAllByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get all for {}", date);
        return service.getAllByDate(date);
    }


    @GetMapping("/{restaurantId}/by-date")
    public List<Vote> getAllByRestaurantIdAndDate(@PathVariable int restaurantId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get all for restaurant {} and date {}", restaurantId, date);
        return service.getAllByRestaurantIdAndDate(restaurantId, date);
    }

    @GetMapping("/winner")
    public List<Restaurant> getWinnerByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get winner for {}", date);
        return service.getWinnerByDate(date);

    }


}
