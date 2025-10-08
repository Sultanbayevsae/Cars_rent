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

    @OneToOne(mappedBy = "photos", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Car car;


    @Lob
    @Column(nullable = false, name = "bytes", columnDefinition = "bytea")
    private Byte[] photo;

    @Column(nullable = false)
    private String contentType;
}
