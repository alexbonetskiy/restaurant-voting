package com.alexbonetskiy.restaurantvoting.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "vote_date"}, name = "vote_unique_user_vote_date_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Vote extends AbstractBaseEntity implements  Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @ToString.Exclude
    protected User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @ToString.Exclude
    protected Restaurant restaurant;


    @Column(name = "vote_date", nullable = false)
    @NotNull
    protected LocalDate date;

    public Vote(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }
}
