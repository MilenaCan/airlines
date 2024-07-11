package com.example.airlines.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Long id;

    @Column(name = "date_of_creation", nullable = false)
    private Date dateOfCreation;

    @Column(name = "reserved_seats", nullable = false)
    private Integer reservedSeats;

    @Column(name = "reservation_status", nullable = false)
    private String reservationStatus;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

}
