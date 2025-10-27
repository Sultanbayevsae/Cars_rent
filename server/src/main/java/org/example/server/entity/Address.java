package org.example.server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "addresses")
public class Address extends Base{

    @Column(name = "city_or_town", length = 100, nullable = false)
    private String cityOrTown;

    @Column(name = "details", length = 100, nullable = false)
    private String details;
}
