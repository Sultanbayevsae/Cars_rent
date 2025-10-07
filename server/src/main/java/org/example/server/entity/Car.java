package org.example.server.entity;

import jakarta.persistence.Column;

public class Car extends Base{
    @Column(nullable = false, unique = true)
    private String name;
    @Column(name ="car_number", nullable = false, unique = true)
    private String carNumber;
    @Column(nullable = false)
    private int gasUsed;
    @Column(name = "price_per_day", nullable = false)
    private int pricePerDay;
    @Column(name = "auto_or_manual", nullable = false)
    private Boolean auto_or_manual;
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;
    @Column(nullable = false)
    private int seats;
    private String imageUrl;

}
