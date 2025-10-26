package org.example.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rental{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "pickup_branch_id", nullable = false)
    private Branch pickupBranch;

    @ManyToOne
    @JoinColumn(name = "dropoff_branch_id", nullable = false)
    private Branch dropoffBranch;

    @ManyToOne
    @JoinColumn(name = "billing_address_id", nullable = false)
    private Address billingAddress;

    @Column(name = "rental_start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "rental_end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "rental_status", nullable = false)
    private RentalStatus rentalStatus;

    @OneToOne(mappedBy = "rental", cascade = CascadeType.ALL)
    private Payment payment;
}
