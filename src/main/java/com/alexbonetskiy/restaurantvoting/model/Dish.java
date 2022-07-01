package com.alexbonetskiy.restaurantvoting.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "date_time", "restaurant_id"}, name = "dish_unique_name_datetime_restaurant_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends AbstractNamedEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "price", nullable = false)
    @NotNull
    @Positive
    private long price;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

}
