package com.geekster.RestaurantManagementService.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authToken")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTImeStamp;

    public AuthenticationToken(User user){
        this.user = user;
        this.tokenCreationTImeStamp =LocalDateTime.now();
        this.tokenValue =UUID.randomUUID().toString();
    }

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    User user;
}
