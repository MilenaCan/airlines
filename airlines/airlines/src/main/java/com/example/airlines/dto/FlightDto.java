package com.example.airlines.dto;

import com.example.airlines.model.AirCompany;
import com.example.airlines.model.Airplane;
import com.example.airlines.model.Destination;

import java.util.Date;

public class FlightDto {


    private Integer availableSeats;
    private Date flightDate;
    private Destination destination;

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
