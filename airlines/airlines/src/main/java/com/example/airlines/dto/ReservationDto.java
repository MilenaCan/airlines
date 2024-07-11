package com.example.airlines.dto;

import com.example.airlines.model.Flight;

import java.time.LocalDateTime;

public class ReservationDto {
    private LocalDateTime dateOfCreation;
    private String username;
    private Integer reservedSeats;

    private Long flightId;


    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }


    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }
}
