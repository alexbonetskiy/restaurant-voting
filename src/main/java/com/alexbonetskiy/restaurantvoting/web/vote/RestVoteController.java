package com.alexbonetskiy.restaurantvoting.web.vote;


import com.alexbonetskiy.restaurantvoting.model.Vote;
import com.alexbonetskiy.restaurantvoting.service.VoteService;
import com.alexbonetskiy.restaurantvoting.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = RestVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestVoteController {

    public static final String REST_URL = "/api/votes";

    private VoteService service;


    @GetMapping
    public List<Vote> getAllVotes(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getAllVotes");
        return service.getAllVotes(authUser.id());
    }

    @GetMapping("/today")
    public ResponseEntity<Vote> getVoteForToday(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getVoteForToday");
        return ResponseEntity.of(service.get(authUser.id(), LocalDate.now()));
    }


    @PostMapping
    public ResponseEntity<Vote> createWithLocation(@RequestParam int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        Vote created = service.create(restaurantId, authUser.id());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        service.update(restaurantId, authUser.id());
    }
}



