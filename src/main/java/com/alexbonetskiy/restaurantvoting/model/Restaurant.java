package com.alexbonetskiy.restaurantvoting.model;



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
public class Restaurant extends AbstractNamedEntity implements  Serializable {


    @Serial
    private static final long serialVersionUID = 1L;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OrderBy("dateTime DESC")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @ToString.Exclude
    private List<Dish> menu = new ArrayList<>();


}
