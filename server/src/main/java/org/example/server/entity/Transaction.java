package org.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // PAYMENT, REFUND

    @Enumerated(EnumType.STRING)
    private TransactionStatus status; // SUCCESS, FAILED, PENDING

    private BigDecimal amount;

    private String bankTransactionId; // returned by bank API
    private String description;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private PaymentCard card;

    private LocalDateTime createdAt;
}
