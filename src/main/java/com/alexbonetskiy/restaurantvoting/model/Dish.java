package com.alexbonetskiy.restaurantvoting.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = { "restaurant_id", "name", "dish_date"}, name = "dish_unique_name_dish_date_restaurant_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dish extends AbstractNamedEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "restaurant_id", nullable = false)
    protected Integer restaurantId;

    @Column(name = "price", nullable = false)
    @NotNull
    @Positive
    protected int price;

    @Column(name = "dish_date", nullable = false)
    @NotNull
    protected LocalDate date;

    public Dish(Integer id, String name, Integer restaurantId, int price, LocalDate date) {
        super(id, name);
        this.restaurantId = restaurantId;
        this.price = price;
        this.date = date;
    }
}
