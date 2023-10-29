package com.geekster.RestaurantManagementService.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foods")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Food.class, property = "foodId")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;
    private String foodTitle;
    private String foodDescription;
    private Integer foodPrice;

    @ManyToOne
    @JoinColumn(name = "fk_order_id")
    Order order;
}
