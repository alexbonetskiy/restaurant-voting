package com.alexbonetskiy.restaurantvoting.repository;

import com.alexbonetskiy.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
