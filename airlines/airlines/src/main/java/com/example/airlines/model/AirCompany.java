package com.example.airlines.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "air_companies")
public class AirCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_company_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private  String name;

    @Column(name = "suspended", nullable = false, columnDefinition = "boolean default false")
    private boolean suspended;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
