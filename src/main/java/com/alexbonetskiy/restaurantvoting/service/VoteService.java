package com.alexbonetskiy.restaurantvoting.service;


import com.alexbonetskiy.restaurantvoting.error.IllegalRequestDataException;
import com.alexbonetskiy.restaurantvoting.model.Restaurant;
import com.alexbonetskiy.restaurantvoting.model.Vote;
import com.alexbonetskiy.restaurantvoting.repository.RestaurantRepository;
import com.alexbonetskiy.restaurantvoting.repository.UserRepository;
import com.alexbonetskiy.restaurantvoting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class VoteService {

    private VoteRepository voteRepository;

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    @Getter
    @Setter
    private static LocalTime deadline = LocalTime.of(11, 0);

    public List<Restaurant> getWinnerByDate(LocalDate date) {
        log.info("get winner for {}", date);
        return getWinner(voteRepository.getAllOnlyRestaurantIdyDate(date));
    }

    public List<Vote> getAllByDate(LocalDate date) {
        log.info("get all for {}", date);
        return voteRepository.getAllByDate(date);
    }

    public List<Vote> getAllByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        log.info("get all for restaurant {} and date {}", restaurantId, date);
        return voteRepository.getAllByRestaurantIdAndDate(restaurantId, date);
    }

    public List<Vote> getAllVotes(int id) {
        log.info("getAllVotes {}", id);
        return voteRepository.getAllVotes(id);
    }

    public Optional<Vote> get(int id, LocalDate now) {
        log.info("get vote with id={} for date={}", id, now);
        return voteRepository.getVoteByUSerIdAndDate(id, now);
    }

    @Transactional
    public Vote create(int restaurantId, int userId) {
        if (voteRepository.getVoteByUSerIdAndDate(userId, LocalDate.now()).isPresent()) {
            throw new IllegalRequestDataException("Your today vote is already created");
        }
        checkRestId(restaurantId);
        log.info("create vote");
        Vote vote = new Vote();
        vote.setRestaurant(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id = " + restaurantId + " not found")));
        vote.setDate(LocalDate.now());
        vote.setUser(userRepository.getById(userId));
        return voteRepository.save(vote);
    }

    @Transactional
    public void update(int restaurantId, int userId) {
        if (LocalTime.now().isAfter(deadline)) {
            throw new IllegalRequestDataException("You can't change your vote after " + deadline);
        } else {
            Vote oldVote = get(userId, LocalDate.now()).orElse(null);
            checkRestId(restaurantId);
            log.info("update vote {}", oldVote.getId());
            oldVote.setRestaurant(restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new EntityNotFoundException("Restaurant with id = " + restaurantId + " not found")));
            voteRepository.save(oldVote);
        }
    }

    public List<Restaurant> getWinner(List<Integer> votes) {
        Map<Integer, Long> voteMap = getVoteMap(votes);
        if (votes.isEmpty()) {
            throw new IllegalRequestDataException("There is no votes today yet");
        }
        Long max = Collections.max(voteMap.values());
        List<Integer> winnerIds = voteMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(Collections.max(voteMap.values())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return winnerIds.stream().map(id -> restaurantRepository.findById(id).orElse(null)).collect(Collectors.toList());
    }

    public static Map<Integer, Long> getVoteMap(List<Integer> votes) {
        return votes.stream().collect(Collectors.groupingBy(integer -> integer, Collectors.counting()));
    }


    public void checkRestId(int id) {
        if (restaurantRepository.getAllForToday(LocalDate.now()).stream().noneMatch(r -> r.id() == id)) {
            throw new IllegalRequestDataException("Restaurant id is invalid");
        }
    }


}
