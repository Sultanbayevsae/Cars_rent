//package org.example.server.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.util.UUID;
//
//@Entity
//@Table(name = "transactions")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@EntityListeners(AuditingEntityListener.class)
//public class Transaction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private TransactionType type;
//
//    @Enumerated(EnumType.STRING)
//    private TransactionStatus status;
//
//    private BigDecimal amount;
//
//    @Column(name = "bank_transaction_id")
//    private String bankTransactionId;
//    private String description;
//
//    @ManyToOne
//    @JoinColumn(name = "payment_id", nullable = false)
//    private Payment payment;
//
//    @ManyToOne
//    @JoinColumn(name = "card_id", nullable = false)
//    private PaymentCard card;
//
//    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private Instant createdAt;
//
//    @CreatedBy
//    @Column(name = "created_by",  nullable = false, updatable = false)
//    private UUID createdBy;
//}
