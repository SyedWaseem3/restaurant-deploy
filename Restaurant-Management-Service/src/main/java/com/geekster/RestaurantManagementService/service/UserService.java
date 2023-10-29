package com.geekster.RestaurantManagementService.service;

import com.geekster.RestaurantManagementService.model.AuthenticationToken;
import com.geekster.RestaurantManagementService.model.User;
import com.geekster.RestaurantManagementService.model.dto.PasswordChangerDto;
import com.geekster.RestaurantManagementService.repo.IUserRepo;
import com.geekster.RestaurantManagementService.service.emailUtility.EmailHandler;
import com.geekster.RestaurantManagementService.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    public String userSignUp(User newUser) {
        String email = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser != null){
            return "email already exists, continue with sign in!";
        }

        //passwords are encrypted

        String password = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            newUser.setUserPassword(encryptedPassword);
            userRepo.save(newUser);
            return "User registered!";
        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue, please try again later!";
        }
    }

    public String userSignIn(String email, String password) {
        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null){
            return "Email invalid, please sign up first and then try signing in again!";
        }

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            if(existingUser.getUserPassword().equals(encryptedPassword)){
                AuthenticationToken token = new AuthenticationToken(existingUser);
                if(EmailHandler.sendEmail(email,"otp after login", token.getTokenValue())){
                    authenticationService.createToken(token);
                }else{
                    return "error while generating token!!";
                }

                return "check email for otp/token";
            }else{
                return "Invalid credentials!!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal server error while saving the password!";
        }

    }

    public String userSignOut(String email, String tokenValue) {
        if(authenticationService.authenticate(email, tokenValue)){
            authenticationService.deleteToken(tokenValue);
            return "sign out successful!";
        }else{
            return "Un authenticated access!!";
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepo.findAll();
        for(User user : allUsers){
            user.setOrders(null);
        }
        return allUsers;
    }

    public User getUserBydId(Integer userId) {
        return userRepo.findById(userId).orElseThrow();
    }

    public String updatePassword(PasswordChangerDto passwordChangerDto, Integer userId) {

        String oldPassword = passwordChangerDto.getOldPassword();

        try {
            String encryptOldPassword = PasswordEncryptor.encrypt(oldPassword);

            User sameUser = userRepo.findById(userId).orElseThrow();

            if(sameUser.getUserPassword().equals(encryptOldPassword)){
                String newPassword = passwordChangerDto.getNewPassword();
                sameUser.setUserPassword(newPassword);
                return "Password Changed!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Password doesn't match!";
        }
        return "Un Authenticated access!";
    }
}
