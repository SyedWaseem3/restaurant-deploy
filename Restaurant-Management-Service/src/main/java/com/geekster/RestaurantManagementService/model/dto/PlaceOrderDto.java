package com.geekster.RestaurantManagementService.model.dto;

import com.geekster.RestaurantManagementService.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {
    AuthenticationInputDto authenticationInputDto;
    Order order;
}
