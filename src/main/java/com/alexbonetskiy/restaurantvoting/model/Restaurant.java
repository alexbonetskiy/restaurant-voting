package com.alexbonetskiy.restaurantvoting.model;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@AllArgsConstructor
public class Restaurant extends AbstractNamedEntity implements  Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OrderBy("date DESC")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @ToString.Exclude
    protected List<Dish> dishes = new ArrayList<>();

    public Restaurant(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
        dish.setRestaurantId(this.id);
    }

    public void removeDish(Dish dish) {
        if (dish != null)
        dish.setRestaurantId(null);
        dishes.remove(dish);
    }

}
