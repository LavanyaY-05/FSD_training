package com.roadready.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rating;

    private String comment;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @OneToOne
    private Booking booking;
}
