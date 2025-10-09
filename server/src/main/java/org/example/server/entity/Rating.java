package org.example.server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "ratings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rating extends Base{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments_id")
    private Comments comments;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "target_id", nullable = false)
    private UUID targetId;

    @Column(name = "target_type", nullable = false)
    private String targetType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
