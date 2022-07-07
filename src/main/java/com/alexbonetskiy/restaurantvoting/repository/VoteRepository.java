package com.alexbonetskiy.restaurantvoting.repository;

import com.alexbonetskiy.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer>, BaseRepository<Vote> {

    @Query("SELECT v, v.user, v.restaurant FROM Vote v WHERE v.restaurant.id=:id and v.date=:date")
    List<Vote> getAllByRestaurantIdAndDate(int id, LocalDate date);

    List<Vote> getAllByDate(LocalDate date);

    @Query("SELECT v.restaurant.id FROM Vote v WHERE v.date =:date")
    List<Integer> getAllOnlyRestaurantIdyDate(LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:id ORDER BY v.date DESC")
    List<Vote> getAllVotes(int id);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:id and v.date=:now")
    Optional<Vote> getVoteByUSerIdAndDate(int id, LocalDate now);
}
