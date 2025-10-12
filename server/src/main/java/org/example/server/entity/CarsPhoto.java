package org.example.server.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name= "cars_photo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarsPhoto extends Base{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;


    @Lob
    @Column(nullable = false, name = "bytes", columnDefinition = "bytea")
    private Byte[] bytes;

    @Column(nullable = false)
    private String contentType;
}
