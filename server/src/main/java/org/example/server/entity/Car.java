package org.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments>comments = new ArrayList<>();

    @Column(nullable = false)
    private int seats;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikedCars>carsliked = new ArrayList<>();

    @OneToMany (mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> rentHistories = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "car_photo_id", nullable = false)
    private CarsPhoto carsPhoto;

}
