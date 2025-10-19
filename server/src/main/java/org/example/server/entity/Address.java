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
@Table(name = "address")
public class Address extends Base{

    @Column(length = 100, nullable = false)
    private String cityOrtown;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;


}
