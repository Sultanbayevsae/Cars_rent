package org.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "payment_cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentCard{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "card_number_masked",  nullable = false)
    private String cardNumberMasked;

    @Column(name = "card_holder_name")
    private String cardHolderName;
    @Column(name = "expiry_date")
    private String expiryDate;

    private String token;
    @Column(name = "is_active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
