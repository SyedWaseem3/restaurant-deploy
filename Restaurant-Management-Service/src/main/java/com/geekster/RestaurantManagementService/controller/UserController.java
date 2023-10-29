package com.geekster.RestaurantManagementService.controller;

import com.geekster.RestaurantManagementService.model.Food;
import com.geekster.RestaurantManagementService.model.Order;
import com.geekster.RestaurantManagementService.model.User;
import com.geekster.RestaurantManagementService.model.dto.AuthenticationInputDto;
import com.geekster.RestaurantManagementService.model.dto.PasswordChangerDto;
import com.geekster.RestaurantManagementService.model.dto.PlaceOrderDto;
import com.geekster.RestaurantManagementService.service.OrderService;
import com.geekster.RestaurantManagementService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    //sign up
    @PostMapping("user/signUp")
    public String userSignUp(@RequestBody User newUser){
        return userService.userSignUp(newUser);
    }

    //sign in
    @PostMapping("user/signIn/email/{email}/password/{password}")
    public String userSignIn(@PathVariable String email, @PathVariable String password){
        return userService.userSignIn(email, password);
    }


    //sign out
    @DeleteMapping("user/signOut/email/{email}/tokenValue/{tokenValue}")
    public String userSignOut(@PathVariable String email, @PathVariable String tokenValue){
        return userService.userSignOut(email, tokenValue);
    }

    //order food
    @PostMapping("order")
    public String orderFood(@RequestBody PlaceOrderDto placeOrderDto){
        return orderService.orderFood(placeOrderDto.getAuthenticationInputDto(), placeOrderDto.getOrder());
    }

    //delete order
    @DeleteMapping("cancel/order/{orderId}")
    public String cancelOrder(@RequestBody AuthenticationInputDto authInput, @PathVariable Integer orderId){
        return orderService.cancelOrder(authInput, orderId);
    }

    @PutMapping("updateUserPasswordById/user/{userId}")
    public String updatePassword(@RequestBody PasswordChangerDto passwordChangerDto, @PathVariable Integer userId){
        return userService.updatePassword(passwordChangerDto, userId);
    }
}
