package org.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentCard{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumberMasked;
    private String cardHolderName;
    private String expiryDate;

    private String token; // token from bank api
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
