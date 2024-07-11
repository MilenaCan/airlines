package com.example.airlines.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airplanes")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Long id;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Column(name = "brand", nullable = false)
    private String brand;
}
