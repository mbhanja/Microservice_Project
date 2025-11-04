package com.lcwd.rating.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @Column(name = "rating_id")
    private String ratingId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "feedback")
    private String feedback;
}
