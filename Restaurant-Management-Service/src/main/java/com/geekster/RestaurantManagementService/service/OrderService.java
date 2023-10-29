package com.geekster.RestaurantManagementService.service;

import com.geekster.RestaurantManagementService.model.Food;
import com.geekster.RestaurantManagementService.model.Order;
import com.geekster.RestaurantManagementService.model.User;
import com.geekster.RestaurantManagementService.model.dto.AuthenticationInputDto;
import com.geekster.RestaurantManagementService.model.dto.PlaceOrderDto;
import com.geekster.RestaurantManagementService.repo.IFoodRepo;
import com.geekster.RestaurantManagementService.repo.IOrderRepo;
import com.geekster.RestaurantManagementService.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IFoodRepo foodRepo;

    @Autowired
    FoodService foodService;


    public String orderFood(AuthenticationInputDto authenticationInputDto, Order order) {
        String email = authenticationInputDto.getEmail();
        String tokenValue = authenticationInputDto.getTokenValue();
        if(authenticationService.authenticate(email, tokenValue)){
            User existingUser = userRepo.findFirstByUserEmail(email);
            order.setUser(existingUser);
            order.setOrderCreationTmeStamp(LocalDateTime.now());

            for (Food food : order.getFoods()) {
                food.setOrder(order);
            }
            orderRepo.save(order);
            return "Order placed!!";
        }else{
            return "User not found!";
        }
    }

    public String cancelOrder(AuthenticationInputDto authInput, Integer orderId) {
        String email = authInput.getEmail();
        String tokenValue = authInput.getTokenValue();
        if(authenticationService.authenticate(email, tokenValue)){
            Order existingOrder = orderRepo.findById(orderId).orElseThrow();
            User existingUser = userRepo.findFirstByUserEmail(email);
            if(existingOrder.getUser().equals(existingUser)){
                orderRepo.deleteById(orderId);
                return "Order canceled!!";
            }else{
                return "Un authorized cancel order!!";
            }
        }else{
            return "Un authorized access!";
        }
    }

    public List<Order> getOrders() {
        List<Order> ordersList =  orderRepo.findAll();
        return ordersList;
    }

    public String updateOrderById(AuthenticationInputDto authInput, Integer orderId,Integer foodId, Integer newFoodId) {
        String email = authInput.getEmail();
        String tokenValue = authInput.getTokenValue();

        if(authenticationService.authenticate(email, tokenValue)){
            User userWhoOrdered = userRepo.findFirstByUserEmail(email);
            Order oldOrder = orderRepo.findById(orderId).orElseThrow();

            if(oldOrder.getUser().equals(userWhoOrdered)){
                foodService.changeFood(foodId, newFoodId);
                oldOrder.setOrderCreationTmeStamp(LocalDateTime.now());
            }else{
                return "Cannot update the food!";
            }
            return "Food Updated!";
        }
        return "Un Authenticated access!!";
    }
}
