package org.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branches")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Branch extends Base{
    private String name;

    @Column(length = 100, nullable = false)
    private String cityOrtown;

    @Column(length = 500)
    private String details;

    @OneToMany(mappedBy = "branch")
    private List<Car> car = new ArrayList<>();
}
